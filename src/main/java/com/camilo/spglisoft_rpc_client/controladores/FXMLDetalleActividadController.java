package com.camilo.spglisoft_rpc_client.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Actividad;
import com.camilo.spglisoft_rpc_client.utils.Constantes;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDetalleActividadController implements Initializable {
    @FXML
    private Label lblFechaInicio;
    @FXML
    private Label lblEstado;
    @FXML
    private Label lblFechaFin;
    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblEsfuerzo;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblDesarrollador;

    /*
     * Creador: Camilo Espejo Sánchez.
     * Fecha de creación: Dec 14, 2023.
     * Descripción: Flujo alterno de algunos casos de uso. muestra la informacion del elemento seleccionado.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarInformacion();
    }

    private void inicializarInformacion() {
        Actividad actividad = (Actividad) MainStage.getUserData();
        lblTitulo.setText(actividad.getNombre());
        lblEstado.setText(actividad.getNombreEstado());
        lblFechaInicio.setText(actividad.getFechaInicio());
        lblFechaFin.setText(actividad.getFechaFin());
        lblEsfuerzo.setText(String.valueOf(actividad.getEsfuerzoMinutos()));
        lblDescripcion.setText(actividad.getDescripcion());
        if (actividad.getNombreCompletoDesarrollador().contains("null")) {
            lblDesarrollador.setText("Actividad aun no asignada");
        } else {
            lblDesarrollador.setText(actividad.getNombreCompletoDesarrollador());
        }
    }

    @FXML
    private void btnRegresar() {
        if (SingletonLogin.getInstance().getTipoUsuario().equals(Constantes.USUARIO_DESARROLLADOR)) {
            MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
        } else {
            MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLRPActividades.fxml", 1000, 600);
        }
    }
}
