/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.camilo.spglisoft_rpc_client.modelo.RpcClient;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SidebarRepresentante;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.Spglisoft;

public class FXMLDesarrolladoresSinProyectoController implements Initializable {

    @FXML
    private TableView<Spglisoft.DevsByProjectIdList.DevInfo> tvDesarrolladores;
    @FXML
    private TableColumn<Spglisoft.DevsByProjectIdList.DevInfo, String> colNombre;
    @FXML
    private TableColumn<Spglisoft.DevsByProjectIdList.DevInfo, String> colApellidoPaterno;
    @FXML
    private TableColumn<Spglisoft.DevsByProjectIdList.DevInfo, String> colApellidoMaterno;
    @FXML
    private TableColumn<Spglisoft.DevsByProjectIdList.DevInfo, String> colMatricula;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaDesarrolladores();
    }    

    private void formatearTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
    }
    public void llenarTablaDesarrolladores(){
        tvDesarrolladores.getItems().clear();
        ManagedChannel managedChannel = Grpc
                .newChannelBuilder(RpcClient.TARGET, InsecureChannelCredentials.create()).build();
        List<Spglisoft.DevsByProjectIdList.DevInfo> listaDesarrolladores;
        try{
            RpcClient client = new RpcClient(managedChannel);
            listaDesarrolladores = client.getDevsWithoutProject().getDevsList();
        } finally {
            try {
                managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Alertas.mostrarAlertaErrorConexionBD();
            }
        }
        tvDesarrolladores.getItems().addAll(listaDesarrolladores);
    }


    @FXML
    private void btnCancelar() {
        SidebarRepresentante.irMenuDesarrolladores();
    }

    @FXML
    private void btnAnadirDesarrollador() {
        if(esElementoSeleccionado()){
            ManagedChannel managedChannel = Grpc
                    .newChannelBuilder(RpcClient.TARGET, InsecureChannelCredentials.create()).build();
            Spglisoft.DevsByProjectIdList.DevInfo desarrollador = tvDesarrolladores.getSelectionModel().getSelectedItem();
            int rowsAffected;
            try{
                RpcClient client = new RpcClient(managedChannel);
                rowsAffected = client
                        .addDevToProject(
                                desarrollador.getIdDesarrollador(),
                                SingletonLogin.getInstance().getIdProyectoActual()
                        ).getRowsAffected();
                formatearTabla();
                llenarTablaDesarrolladores();
                MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLRPDesarrolladores.fxml", 1000, 600);
            } finally {
                try {
                    managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    Alertas.mostrarAlertaErrorConexionBD();
                }
            }

            if (rowsAffected == 1) {
                Alertas.mostrarAlertaExito();
            } else {
                Alertas.mostrarAlertaErrorConexionBD();
            }

        } else{
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }
    
    private boolean esElementoSeleccionado() {
        return tvDesarrolladores.getSelectionModel().getSelectedItem() != null;
    }
}
