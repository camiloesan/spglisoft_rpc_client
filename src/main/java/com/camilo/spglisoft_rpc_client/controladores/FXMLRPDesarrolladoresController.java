/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.camilo.spglisoft_rpc_client.modelo.RpcClient;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SidebarRepresentante;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import com.camilo.spglisoft_rpc_client.utils.Utilidades;
import spglisoft.Spglisoft;

public class FXMLRPDesarrolladoresController implements Initializable, ISidebarRPButtons {
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
    private void btnEliminarDesarrollador() {
        if(esElementoSeleccionado()){
            Spglisoft.DevsByProjectIdList.DevInfo desarrollador = tvDesarrolladores.getSelectionModel().getSelectedItem();
            if (Utilidades.mostrarAlertaConfirmacion("Confirmación",
                    "¿Está seguro de que desea eliminar el desarrollador del proyecto?")) {
                ManagedChannel managedChannel = Grpc
                        .newChannelBuilder(RpcClient.TARGET, InsecureChannelCredentials.create()).build();
                int rowsAffected;
                try{
                    RpcClient client = new RpcClient(managedChannel);
                    rowsAffected = client.deleteDevFromProject(desarrollador.getIdDesarrollador()).getRowsAffected();
                    formatearTabla();
                    llenarTablaDesarrolladores();
                } finally {
                    try {
                        managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        Alertas.mostrarAlertaErrorConexionBD();
                    }
                }

                if (rowsAffected == 1) {
                    Alertas.mostrarAlertaExito();
                } else {
                    Utilidades.mostrarAlertaSimple("Error", "No se pudo eliminar al desarrollador", Alert.AlertType.ERROR);
                }
            }
        } else{
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }
    
    private boolean esElementoSeleccionado() {
        return tvDesarrolladores.getSelectionModel().getSelectedItem() != null;
    }

    @FXML
    private void btnAnadirDesarrollador(ActionEvent event) {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLDesarrolladoresSinProyecto.fxml", 1000, 600);
        formatearTabla();
        llenarTablaDesarrolladores();
    }

    @FXML
    private void btnRegresar(MouseEvent event) {
        SidebarRepresentante.irMenuDefectos();
    }

    @Override
    public void btnActividades() {
        SidebarRepresentante.irMenuActividades();
    }

    @Override
    public void btnDefectos() {

    }

    @Override
    public void btnDesarrolladores() {
        //vista actual
    }

    @Override
    public void btnSolicitudesCambio() {
        SidebarRepresentante.irConsultarSolicitudesCambio();
    }

    @Override
    public void btnInformacionProyecto() {
        SidebarRepresentante.irMenuInformacionProyeto();
    }

    @Override
    public void btnRegresar() {
        SidebarRepresentante.irMenuProyectos();
    }
}
