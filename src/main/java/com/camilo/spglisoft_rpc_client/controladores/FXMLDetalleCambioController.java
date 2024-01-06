package com.camilo.spglisoft_rpc_client.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Cambio;

import java.net.URL;
import java.util.ResourceBundle;

/*
 * Creador: Camilo Espejo Sánchez.
 * Fecha de creación: Dec 14, 2023.
 * Descripción: Flujo alterno de algunos casos de uso.
 * Muestra el desglose de la informacion de algun objeto seleccionado.
 */

public class FXMLDetalleCambioController implements Initializable {
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblTipoCambio;
    @FXML
    private Label lblDesarrollador;
    @FXML
    private Label lblDescripcion;

    private Cambio cambioActual;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarInformacion();
    }

    private void inicializarInformacion() {
        cambioActual = (Cambio) MainStage.getUserData();
        lblNombre.setText("Detalles del cambio [" + cambioActual.getNombre() + "]");
        lblTipoCambio.setText(cambioActual.getTipoCambio());
        lblDesarrollador.setText(cambioActual.getNombreCompletoDesarrollador());
        lblDescripcion.setText(cambioActual.getDescripcion());
    }

    @FXML
    private void btnRegresar() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLRPCambios.fxml", 1000, 600, FXMLRPCambiosController.SolicitudSeleccionada);
    }
}
