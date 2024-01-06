/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.camilo.spglisoft_rpc_client.modelo.ResultadoOperacion;
import com.camilo.spglisoft_rpc_client.modelo.dao.SolicitudCambioDAO;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Desarrollador;
import com.camilo.spglisoft_rpc_client.modelo.pojo.SolicitudCambio;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import com.camilo.spglisoft_rpc_client.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author lecap
 */
public class FXMLRegistrarSolicitudCambioController implements Initializable {
    
    private Desarrollador desarrollador;
    
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextArea taAccionPropuesta;
    @FXML
    private TextArea taDescripcionCambio;
    @FXML
    private TextArea taRazonCambio;
    @FXML
    private TextArea taImpactoCambio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btVolver(ActionEvent event) {
        Utilidades.mostrarAlertaSimple("Registrar solicitud de cambio", 
                "Registro cancelado", Alert.AlertType.INFORMATION);
        cerrarStage();
    }
    
    private void registrarNuevaSolicitud(SolicitudCambio solicitud){
        if (!camposFaltantes()) {
            ResultadoOperacion resultado = new ResultadoOperacion();
            try {
                resultado = SolicitudCambioDAO.registrarSolicitud(solicitud);
                if (!resultado.isError()) {
                    Utilidades.mostrarAlertaSimple("Registro", resultado.getMensaje(),
                            Alert.AlertType.INFORMATION);
                    cerrarStage();
                }
            } catch (SQLException e) {
                Utilidades.mostrarAlertaSimple("Registro", resultado.getMensaje(),
                        Alert.AlertType.ERROR);
            }
        } else {
            Alertas.mostrarAlertaCamposFaltantes();
        }
    }
    
    private boolean camposFaltantes(){
        return tfTitulo.getText().trim().isEmpty() || taAccionPropuesta.getText().trim().isEmpty()
                || taDescripcionCambio.getText().trim().isEmpty() || taRazonCambio.getText().trim().isEmpty()
                || taImpactoCambio.getText().trim().isEmpty();
    }

    @FXML
    private void btRegistrarSolicitud(ActionEvent event) {
       registro();
    }
    
    private void registro(){
        String impactoCambio = taImpactoCambio.getText();
        String nombre = tfTitulo.getText();
        String descripcion = taDescripcionCambio.getText();
        String accionPropuesta = taAccionPropuesta.getText();
        String razonCambio = taRazonCambio.getText();
        
        SolicitudCambio nuevaSolicitud = new SolicitudCambio();
        
        nuevaSolicitud.setNombreSolicitud(nombre);
        nuevaSolicitud.setDescripcion(descripcion);
        nuevaSolicitud.setAccionPropuesta(accionPropuesta);
        nuevaSolicitud.setAccionPropuesta(accionPropuesta);
        nuevaSolicitud.setRazonCambio(razonCambio);
        nuevaSolicitud.setImpactoCambio(impactoCambio);
        nuevaSolicitud.setIdProyecto(desarrollador.getIdProyecto());
        nuevaSolicitud.setIdDesarrollador(desarrollador.getIdDesarrollador());
        nuevaSolicitud.setIdEstadoSolicitud(2);
        
        registrarNuevaSolicitud(nuevaSolicitud);
    }
    
    public void iniciarDatos(){
        this.desarrollador = SingletonLogin.getInstance().getDesarrollador();
    }
    
    private void cerrarStage(){
        Stage escenario = (Stage) tfTitulo.getScene().getWindow();
        escenario.close();
    }
}
