/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.camilo.spglisoft_rpc_client.modelo.dao.ProyectoDAO;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Proyecto;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/*
 * Creador: Camilo Espejo Sánchez.
 * Fecha de creación: Dec 14, 2023.
 * Descripción: Muestra los proyectos de los que esta a cargo el representante loggeado.
 */
public class FXMLRPMenuPrincipalController implements Initializable {

    @FXML
    private TableView<Proyecto> tablaProyectos;
    @FXML
    private TableColumn<Proyecto, String> columnaNombre;
    @FXML
    private TableColumn<Proyecto, String> columnaEstado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaProyectos();
    }    
    
    private void formatearTabla() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProyecto"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("nombreEstado"));
    }
    
    private void llenarTablaProyectos() {
        tablaProyectos.getItems().clear();
        List<Proyecto> listaProyectos = new ArrayList<>();
        int idRepresentante = SingletonLogin.getInstance().getProjectManager().getIdRepresentante();
        try {
            listaProyectos = ProyectoDAO.obtenerProyectosPorIdRepresentante(idRepresentante);
        } catch (SQLException ex) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tablaProyectos.getItems().addAll(listaProyectos);
    }

    @FXML
    private void btnDetails() {
        if (tablaProyectos.getSelectionModel().getSelectedItem() != null) {
            SingletonLogin.getInstance().setIdProyectoActual(tablaProyectos.getSelectionModel().getSelectedItem().getIdProyecto());
            SingletonLogin.getInstance().setProyectoActual(tablaProyectos.getSelectionModel().getSelectedItem());
            MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLRPActividades.fxml", 1000, 600);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }
    
    @FXML
    private void btnLogOut() {
        SingletonLogin.cleanDetails();
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLLogin.fxml", 600, 400);
    }
}
