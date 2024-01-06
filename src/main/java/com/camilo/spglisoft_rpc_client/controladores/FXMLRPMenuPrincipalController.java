package com.camilo.spglisoft_rpc_client.controladores;

import com.camilo.spglisoft_rpc_client.modelo.RpcClient;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import spglisoft.Spglisoft;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class FXMLRPMenuPrincipalController implements Initializable {

    @FXML
    private TableView<Spglisoft.ProjectInfo> tablaProyectos;
    @FXML
    private TableColumn<Spglisoft.ProjectInfo, String> columnaNombre;
    @FXML
    private TableColumn<Spglisoft.ProjectInfo, String> columnaEstado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaProyectos();
    }    
    
    private void formatearTabla() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProyecto"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }
    
    private void llenarTablaProyectos() {
        tablaProyectos.getItems().clear();
        ManagedChannel managedChannel = Grpc
                .newChannelBuilder(RpcClient.TARGET, InsecureChannelCredentials.create()).build();
        Spglisoft.ProjectList response;
        List<Spglisoft.ProjectInfo> listaProyectos;
        try {
            RpcClient client = new RpcClient(managedChannel);
            response = client
                    .getProjectsByManagerId(SingletonLogin.getInstance().getProjectManager().getIdRepresentante());
            listaProyectos = response.getProyectosList();
        }
        finally {
            try {
                managedChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Alertas.mostrarAlertaErrorConexionBD();
            }
        }
        tablaProyectos.getItems().addAll(listaProyectos);
    }

    @FXML
    private void btnDetails() {
        if (tablaProyectos.getSelectionModel().getSelectedItem() != null) {
            SingletonLogin.getInstance().setIdProyectoActual(tablaProyectos.getSelectionModel().getSelectedItem().getIdProyecto());
            SingletonLogin.getInstance().setProyectoActual(tablaProyectos.getSelectionModel().getSelectedItem());
            MainStage.changeView("FXMLRPActividades.fxml", 1000, 600);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }
    
    @FXML
    private void btnLogOut() {
        SingletonLogin.cleanDetails();
        MainStage.changeView("FXMLLogin.fxml", 600, 400);
    }
}
