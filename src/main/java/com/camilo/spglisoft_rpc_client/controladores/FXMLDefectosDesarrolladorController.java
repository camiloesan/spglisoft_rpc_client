package com.camilo.spglisoft_rpc_client.controladores;

import java.io.IOException;

import com.camilo.spglisoft_rpc_client.modelo.dao.DefectoDAO;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Defecto;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SidebarDesarrollador;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import com.camilo.spglisoft_rpc_client.utils.Utilidades;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLDefectosDesarrolladorController implements Initializable, ISidebarDesarrollador {
    @FXML
    TableView<Defecto> tvDefectos;
    @FXML
    TableColumn<Defecto, String> colNombre;
    @FXML
    TableColumn<Defecto, String> colFechaReporte;
    @FXML
    TableColumn<Defecto, String> colTipoDefecto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatearTabla();
        llenarTablaDefectos();
    }

    private void formatearTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreDefectoString"));
        colFechaReporte.setCellValueFactory(new PropertyValueFactory<>("fechaReporte"));
        colTipoDefecto.setCellValueFactory(new PropertyValueFactory<>("nombreTipoDefecto"));
    }

    private void llenarTablaDefectos() {
        tvDefectos.getItems().clear();
        List<Defecto> listaDefectos = new ArrayList<>();
        try {
            listaDefectos = DefectoDAO
                    .obtenerDefectosRegistradosPorDesarrollador
                    (SingletonLogin.getInstance().getDesarrollador().getIdDesarrollador());
        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvDefectos.getItems().addAll(listaDefectos);
    }

    @FXML
    public void btnVerDetalleDefecto() {
        Defecto defecto = tvDefectos.getSelectionModel().getSelectedItem();

        if (defecto != null) {
            MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLDetalleDefecto.fxml", 1000, 600, defecto);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    @Override
    @FXML
    public void btnActividades() {
        SidebarDesarrollador.irMenuActividades();
    }

    @Override
    public void btnCambios() {

    }

    @Override
    @FXML
    public void btnDefectos() {

    }

    @Override
    public void btnSolicitudesCambio() {
        SidebarDesarrollador.irMenuSolicituesCambio();
    }

    @Override
    public void btnInformacionProyecto() {
        SidebarDesarrollador.irMenuInformacionProyecto();
    }

    @Override
    @FXML
    public void btnCerrarSesion() {
        SidebarDesarrollador.cerrarSesionDesarrollador();
    }

    @FXML
    private void btRegistrarDefecto(ActionEvent event) {
        irRegistrarDefecto();
    }
    
    private void irRegistrarDefecto() {
        try {
            FXMLLoader loader = Utilidades.cargarVista("/com/camilo/spglisoft_rpc_client/vistas/FXMLRegistrarDefecto.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLRegistrarDefectoController controlador = loader.getController();
            controlador.iniciarDatos();
            
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Registrar defecto");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error",
                    "No se puede mostrar la ventana", Alert.AlertType.ERROR);
        }
    }
}
