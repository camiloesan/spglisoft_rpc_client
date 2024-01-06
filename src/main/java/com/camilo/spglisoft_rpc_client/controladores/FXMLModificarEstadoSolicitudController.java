package com.camilo.spglisoft_rpc_client.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import com.camilo.spglisoft_rpc_client.modelo.dao.SolicitudCambioDAO;
import com.camilo.spglisoft_rpc_client.modelo.pojo.EstadoSolicitud;
import com.camilo.spglisoft_rpc_client.modelo.pojo.SolicitudCambio;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.Utilidades;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/*
 * Creador: Camilo Espejo Sánchez.
 * Fecha de creación: Dec 14, 2023.
 * Descripción: Caso de uso: modificar estado solicitud, se encarga de actualizar el estado
 * de la solicitud para despues poder acceder a otros apartados del sistema.
 */

public class FXMLModificarEstadoSolicitudController implements Initializable {
    @FXML
    ComboBox<EstadoSolicitud> cbEstadoSolicitud;
    @FXML
    Label lblDescripcion;

    SolicitudCambio solicitudCambio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatearCbEstadoSolicitud();
        inicializarInformacion();
    }

    private void inicializarInformacion() {
        solicitudCambio = (SolicitudCambio) MainStage.getUserData();
        lblDescripcion.setText("Descripcion: " + solicitudCambio.getDescripcion());
        cbEstadoSolicitud.getSelectionModel().select(solicitudCambio.getIdEstadoSolicitud()-1);
    }

    private void formatearCbEstadoSolicitud() {
        ObservableList<EstadoSolicitud> estadosActividad = FXCollections.observableArrayList();
        List<EstadoSolicitud> estados = SolicitudCambioDAO.obtenerEstadosSolicitud();
        estadosActividad.addAll(estados);
        cbEstadoSolicitud.setItems(estadosActividad);
    }

    @FXML
    private void btnGuardar() {
        int idEstadoSolicitud = cbEstadoSolicitud.getSelectionModel().getSelectedItem().getIdEstadoSolicitud();

        boolean confirmacion = Utilidades.mostrarAlertaConfirmacion("Confirmar" ,"Está seguro de que desea " +
                "actualizar el estado?");

        if (confirmacion) {
            try {
                SolicitudCambioDAO.actualizarEstadoSolicitud(idEstadoSolicitud, solicitudCambio.getIdSolicitud());
                Utilidades.mostrarAlertaSimple("Modificación exitosa",
                        "Se ha cambiado el estado a: " + cbEstadoSolicitud
                                .getSelectionModel().getSelectedItem().getEstadoSolicitud(),
                        Alert.AlertType.INFORMATION);
                MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLConsultarSolicitudes.fxml", 1000, 600);
            } catch (SQLException e) {
                Alertas.mostrarAlertaErrorConexionBD();
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnCancelar() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLConsultarSolicitudes.fxml", 1000, 600);
    }
}
