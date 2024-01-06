package com.camilo.spglisoft_rpc_client.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Defecto;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDetalleDefectoController implements Initializable {
    @FXML
    Label lblNombre;
    @FXML
    Label lblFechaReporte;
    @FXML
    Label lblEstadoDefecto;
    @FXML
    Label lblDescripcion;
    @FXML
    Label lblTipoDefecto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Defecto defecto = (Defecto) MainStage.getUserData();
        lblNombre.setText(defecto.getNombreDefectoString());
        lblFechaReporte.setText(defecto.getFechaReporte());
        lblEstadoDefecto.setText(defecto.getNombreEstadoDefecto());
        lblDescripcion.setText(defecto.getDescripcion());
        lblTipoDefecto.setText(defecto.getNombreTipoDefecto());
    }

    @FXML
    private void btnRegresar() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLDefectosDesarrollador.fxml", 1000, 600);
    }
}
