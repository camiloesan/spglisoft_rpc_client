/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.camilo.spglisoft_rpc_client.modelo.ResultadoOperacion;
import com.camilo.spglisoft_rpc_client.modelo.dao.ActividadDAO;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Actividad;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import com.camilo.spglisoft_rpc_client.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author lecap
 */
public class FXMLRegistrarActividadController implements Initializable {

    @FXML
    private TextField tfNombreActividad;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TextField tfEsfuerzoEstimado;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfEsfuerzoEstimado.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfEsfuerzoEstimado.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }    

    @FXML
    private void btnRegistrarActividad(ActionEvent event) {
        generarNuevaActividad();
    }
    
    private void generarNuevaActividad() {
        if (!fechasFaltantes() && validarEsfuerzo()) {
            String fechaInicio = dpFechaInicio.getValue().toString();
            String fechaFin = dpFechaFin.getValue().toString();
            String nombreActividad = tfNombreActividad.getText();
            String descripcion = taDescripcion.getText();
            String esfuerzoEstimadoString = tfEsfuerzoEstimado.getText().trim();
            int esfuerzoEstimado = Integer.parseInt(esfuerzoEstimadoString);
            
            Actividad nuevaActividad = new Actividad();
            nuevaActividad.setNombre(nombreActividad);
            nuevaActividad.setDescripcion(descripcion);
            nuevaActividad.setEsfuerzoMinutos(esfuerzoEstimado);
            nuevaActividad.setFechaInicio(fechaInicio);
            nuevaActividad.setFechaFin(fechaFin);
            nuevaActividad.setIdEstado(2);
            nuevaActividad.setIdProyecto(SingletonLogin.getInstance().getIdProyectoActual());
        
            registrarNuevaActividad(nuevaActividad);
        } else {
            Alertas.mostrarAlertaCamposFaltantes();
        }
    }
    
    private boolean validarEsfuerzo(){
        String esfuerzo = tfEsfuerzoEstimado.getText().trim();
        return !esfuerzo.isEmpty();
    }
    
    private void registrarNuevaActividad(Actividad nuevaActividad) {
        if (!camposFaltantes() && !fechasFaltantes()) {
            ResultadoOperacion resultado = new ResultadoOperacion();
            if (!fechasValidas()) {
                try {
                    resultado = ActividadDAO.registrarActividad(nuevaActividad);
                    Utilidades.mostrarAlertaSimple("Registro", resultado.getMensaje(),
                            Alert.AlertType.INFORMATION);
                    cerrarStage();
                } catch (SQLException e) {
                    Utilidades.mostrarAlertaSimple("Registro", resultado.getMensaje(),
                        Alert.AlertType.ERROR);
                    e.printStackTrace();
                }
                
            } else {
                Utilidades.mostrarAlertaSimple("Registro", "Las fechas no son validas",
                        Alert.AlertType.INFORMATION);
            }
        } else {
            Alertas.mostrarAlertaCamposFaltantes();
        }
    }

    @FXML
    private void btnVolver(ActionEvent event) {
        Utilidades.mostrarAlertaSimple("Registrar actividad", "Registro cancelado",
                Alert.AlertType.INFORMATION);
        cerrarStage();
    }
    
    private boolean camposFaltantes() {
        return tfNombreActividad.getText().trim().isEmpty() || taDescripcion.getText()
                .trim().isEmpty() || tfEsfuerzoEstimado.getText().trim().isEmpty();
    }
    
    private boolean fechasFaltantes() {
        return dpFechaFin.getValue() == null || dpFechaInicio.getValue() == null;
    }
    
    private boolean fechasValidas() {
        LocalDate fechaActual = LocalDate.now();
        return dpFechaInicio.getValue().isAfter(dpFechaFin.getValue()) 
                || dpFechaFin.getValue().isBefore(dpFechaInicio.getValue()) 
                || dpFechaInicio.getValue().equals(dpFechaFin.getValue())
                || dpFechaInicio.getValue().isBefore(fechaActual)
                || dpFechaFin.getValue().isBefore(fechaActual);

    }
    
    private void cerrarStage() {
        Stage escenario = (Stage) tfEsfuerzoEstimado.getScene().getWindow();
        escenario.close();
    }
}
