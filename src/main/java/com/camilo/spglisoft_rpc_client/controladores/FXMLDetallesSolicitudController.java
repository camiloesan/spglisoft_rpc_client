/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.camilo.spglisoft_rpc_client.modelo.pojo.SolicitudCambio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lecap
 */
public class FXMLDetallesSolicitudController implements Initializable {
    
    private SolicitudCambio solicitud;
    private String nombreSolicitante;

    @FXML
    private Label lbTituloSolicitud;
    @FXML
    private Label lbNombreSolicitante;
    @FXML
    private Label lbFechaSolicitud;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TextArea taRazonCambio;
    @FXML
    private TextArea taAccionPropuesta;
    @FXML
    private TextArea taImpactoCambio;
    @FXML
    private Label lbNombreProyecto;
    @FXML
    private Label lbEstadoSolicitud;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        taDescripcion.setEditable(false);
        taRazonCambio.setEditable(false);
        taAccionPropuesta.setEditable(false);
        taImpactoCambio.setEditable(false);
    }    

    @FXML
    private void btVolver(ActionEvent event) {
        cerrarVentana();
    }
    
    public void iniciarInformacion(SolicitudCambio solicitud) {
        try {
            this.solicitud = solicitud;
            this.nombreSolicitante = solicitud.getNombreDesarrollador();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            //String fechaString = formato.format(solicitud.getFechaSolicitud());
            String fechaFormateada = solicitud.getFechaFormateada();
            lbTituloSolicitud.setText("Nombre de la solicitud: " + solicitud.getNombreSolicitud());
            lbNombreProyecto.setText("Proyecto: " + solicitud.getNombreProyecto());
            lbEstadoSolicitud.setText("Estado de la solicitud: " + solicitud.getEstadoSolicitud());
            taImpactoCambio.setText(solicitud.getImpactoCambio());
            lbNombreSolicitante.setText("Nombre del solicitante: " + nombreSolicitante);
            lbFechaSolicitud.setText("Fecha de registro de la solicitud: " + fechaFormateada);
            taDescripcion.setText(solicitud.getDescripcion());
            taRazonCambio.setText(solicitud.getRazonCambio());
            taAccionPropuesta.setText(solicitud.getAccionPropuesta());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cerrarVentana() {
        Stage escena = (Stage) lbFechaSolicitud.getScene().getWindow();
        escena.close();
    }
}
