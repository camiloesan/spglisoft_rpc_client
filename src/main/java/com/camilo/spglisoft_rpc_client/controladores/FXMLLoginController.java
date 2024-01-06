/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.camilo.spglisoft_rpc_client.modelo.RpcClient;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.Constantes;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;

import java.util.concurrent.TimeUnit;

import javafx.scene.control.Alert;
import com.camilo.spglisoft_rpc_client.utils.Utilidades;
import spglisoft.Spglisoft;

/*
 * Creador: Camilo Espejo Sánchez.
 * Fecha de creación: Dec 14, 2023.
 * Descripción: Hace el despacho del inicio de sesion,
 * discrimina entre desarrollador o representante para despues
 * redirigir a la pantalla correspondiente.
 */

public class FXMLLoginController {

    public Button buttonContinue;
    String formato = "zs[a-zA-Z0-9]+";
    
    @FXML
    TextField tfEmail;
    @FXML
    PasswordField tfPassword;
    
    public void initialize() {
        tfEmail.setText("1500");
        tfPassword.setText("groyper");
    }
    
    @FXML
    private void btnLogin() {
        iniciarSesion();
    }
    
    private void iniciarSesion(){
        if (!camposVacios()) {
            String usuario = tfEmail.getText().trim();
            String contrasena = tfPassword.getText().trim();
            if (usuario.matches(formato)) {
                try {
                    ManagedChannel managedChannel
                            = Grpc.newChannelBuilder(RpcClient.TARGET, InsecureChannelCredentials.create()).build();
                    Spglisoft.DeveloperCredentials developer;
                    try {
                        RpcClient client = new RpcClient(managedChannel);
                        developer = client.getDeveloper(usuario, contrasena);
                        System.out.println(developer.getNombre() + " " + developer.getIdDesarrollador());
                    } finally {
                        managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                    }

                    if (developer.getIdDesarrollador() != 0) {
                        SingletonLogin singletonLogin = SingletonLogin.getInstance();
                        singletonLogin.setDeveloper(developer);
                        singletonLogin.setTipoUsuario(Constantes.USUARIO_DESARROLLADOR);
                        Utilidades.mostrarAlertaSimple("Bienvenido", "Bienvenido al SPGLISOFT", Alert.AlertType.INFORMATION);
                        MainStage.changeView("FXMLActividadesDesarrollador.fxml", 1000, 600);
                    } else {
                        Alertas.mostrarAlertaLoginFallido();
                    }
                } catch (InterruptedException e) {
                    Alertas.mostrarAlertaErrorConexionBD();
                }
            } else if (usuario.matches("\\d+")) {
                try {
                    ManagedChannel managedChannel
                            = Grpc.newChannelBuilder(RpcClient.TARGET, InsecureChannelCredentials.create()).build();
                    Spglisoft.ProjectManagerCredentials projectManager;
                    try {
                        RpcClient client = new RpcClient(managedChannel);
                        projectManager = client.getProjectManager(usuario, contrasena);
                        System.out.println(projectManager.getNombre() + " " + projectManager.getIdRepresentante());
                    } finally {
                        managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                    }

                    if (projectManager.getIdRepresentante() != 0) {
                        SingletonLogin singletonLogin = SingletonLogin.getInstance();
                        singletonLogin.setProjectManager(projectManager);
                        singletonLogin.setTipoUsuario(Constantes.USUARIO_REPRESENTANTE);
                        Utilidades.mostrarAlertaSimple("Bienvenido", "Bienvenido al SPGLISOFT", Alert.AlertType.INFORMATION);
                        MainStage.changeView("FXMLRPMenuPrincipal.fxml", 1000, 600);
                    } else {
                        Alertas.mostrarAlertaLoginFallido();
                    }
                } catch (InterruptedException e) {
                    Alertas.mostrarAlertaErrorConexionBD();
                }
            } else {
                Alertas.mostrarAlertaLoginFallido();
            }
        } else {
            Utilidades.mostrarAlertaSimple("Campos faltantes", "Ingrese los campos faltantes",
                    Alert.AlertType.INFORMATION);
        }
    }
    
    private boolean camposVacios(){
        return tfEmail.getText().trim().isEmpty() || tfPassword.getText().trim().isEmpty();
    }
}
