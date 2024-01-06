/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import com.camilo.spglisoft_rpc_client.modelo.RpcClient;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Actividad;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import com.camilo.spglisoft_rpc_client.utils.Utilidades;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.Spglisoft;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class FXMLAsignarActividadController implements Initializable {
    @FXML
    private TableView<Spglisoft.DevsByProjectIdList.DevInfo> tvDesarrolladores;
    @FXML
    private TableColumn<Spglisoft.DevsByProjectIdList.DevInfo, String> colApellidoPaterno;
    @FXML
    private TableColumn<Spglisoft.DevsByProjectIdList.DevInfo, String> colNombre;
    @FXML
    private TableColumn<Spglisoft.DevsByProjectIdList.DevInfo, String> colApellidoMaterno;
    @FXML
    private TableColumn<Spglisoft.DevsByProjectIdList.DevInfo, String> colMatricula;
    @FXML
    private Label lblTitulo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarInformacion();
        formatearTabla();
        llenarTabla();
    }

    private void inicializarInformacion() {
        Actividad actividad = (Actividad) MainStage.getUserData();
        lblTitulo.setText("Asignar Desarrollador Actividad [" + actividad.getNombre() + "]");
    }

    private void formatearTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
    }

    private void llenarTabla() {
        tvDesarrolladores.getItems().clear();
        ManagedChannel managedChannel = Grpc
                .newChannelBuilder(RpcClient.TARGET, InsecureChannelCredentials.create()).build();
        Spglisoft.DevsByProjectIdList response;
        List<Spglisoft.DevsByProjectIdList.DevInfo> listaDesarrolladores;
        try {
            RpcClient client = new RpcClient(managedChannel);
            response = client.getDevsByProjectId(SingletonLogin.getInstance().getIdProyectoActual());
            listaDesarrolladores = response.getDevsList();
        }
        finally {
            try {
                managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Alertas.mostrarAlertaErrorConexionBD();
            }
        }

        tvDesarrolladores.getItems().addAll(listaDesarrolladores);
    }

    @FXML
    private void btnAsignarActividad() {
        Actividad actividad = (Actividad) MainStage.getUserData();
        Spglisoft.DevsByProjectIdList.DevInfo desarrollador = tvDesarrolladores.getSelectionModel().getSelectedItem();
        if (desarrollador != null) {
            ManagedChannel managedChannel = Grpc
                    .newChannelBuilder(RpcClient.TARGET, InsecureChannelCredentials.create()).build();
            int rowsAffected;
            try {
                RpcClient client = new RpcClient(managedChannel);
                rowsAffected = client.assignActivity(actividad.getIdActividad(), desarrollador.getIdDesarrollador()).getRowsAffected();
                MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLRPActividades.fxml", 1000, 600);
            } finally {
                try {
                    managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    Alertas.mostrarAlertaErrorConexionBD();
                }
            }

            if (rowsAffected > 0) {
                Utilidades.mostrarAlertaSimple("Éxito",
                        "Se ha asignado esta actividad al desarrollador: "
                                + desarrollador.getNombre() + " "
                                + desarrollador.getApellidoPaterno() + " "
                                + desarrollador.getApellidoMaterno(),
                        Alert.AlertType.INFORMATION);
            } else {
                Alertas.mostrarAlertaErrorConexionBD();
            }
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    @FXML
    private void btnCancelar() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLRPActividades.fxml", 1000, 600);
    }
}
