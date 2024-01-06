package com.camilo.spglisoft_rpc_client.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.camilo.spglisoft_rpc_client.modelo.dao.ProyectoDAO;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Proyecto;
import com.camilo.spglisoft_rpc_client.utils.SidebarDesarrollador;
import com.camilo.spglisoft_rpc_client.utils.SidebarRepresentante;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
 * Creador: Camilo Espejo Sánchez.
 * Fecha de creación: Dec 14, 2023.
 * Descripción: Muestra el detalle del proyecto en el que se esta navegando actualmente
 * dentro del sistema.
 */

public class FXMLDetalleProyectoController implements Initializable {

    @FXML
    private Label lblFechaInicio;

    @FXML
    private Label lblFechaFin;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblRepresentanteProyecto;

    @FXML
    private Label lblNombreProyecto;

    @FXML
    private Label lblEstadoProyecto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatearInformacion();
    }

    private void formatearInformacion() {
        Proyecto proyecto = new Proyecto();
        try {
            if (SingletonLogin.getInstance().getDesarrollador() != null) {
                proyecto = ProyectoDAO
                        .obtenerProyectoPorIdDesarrollador(SingletonLogin.getInstance().getDesarrollador().getIdDesarrollador());
            } else {
                proyecto = SingletonLogin.getInstance().getProyectoActual();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblNombreProyecto.setText("Proyecto: " + proyecto.getNombreProyecto());
        lblDescripcion.setText(proyecto.getDescripcion());
        lblFechaInicio.setText(proyecto.getFechaInicio());
        lblFechaFin.setText(proyecto.getFechaFin());
        lblEstadoProyecto.setText(proyecto.getNombreEstado());
        lblRepresentanteProyecto.setText(proyecto.getNombreRepresentante()
                + " " + proyecto.getApellidoPaternoRepresentante()
                + " " + proyecto.getApellidoMaternoRepresentante());
    }

    @FXML
    private void btnRegresar() {
        if (SingletonLogin.getInstance().getDesarrollador() != null) {
            SidebarDesarrollador.irMenuActividades();
        } else {
            SidebarRepresentante.irMenuActividades();
        }
    }
}
