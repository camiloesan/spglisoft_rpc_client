/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import com.camilo.spglisoft_rpc_client.modelo.dao.ActividadDAO;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Actividad;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SidebarDesarrollador;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;


/*
 * Creador: Camilo Espejo Sánchez.
 * Fecha de creación: Dec 14, 2023.
 * Descripción: Caso de uso: consultar actividades, muestra las actividades propias de un desarrollador
 * en particular.
 */

public class FXMLActividadesDesarrolladorController implements Initializable, ISidebarDesarrollador {
    @FXML
    private TableView<Actividad> tvActividades;
    @FXML
    private TableColumn<Actividad, String> colFechaInicio;
    @FXML
    private TableColumn<Actividad, String> colEstado;
    @FXML
    private TableColumn<Actividad, String> colTitulo;
    @FXML
    private TableColumn<Actividad, String> colFechaFin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaActividades();
    }

    private void formatearTabla() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("nombreEstado"));
    }

    private void llenarTablaActividades() {
        tvActividades.getItems().clear();
        List<Actividad> listaActividades = new ArrayList<>();
        try {
            listaActividades = ActividadDAO
                    .obtenerActividadesAsignadasPorIdDesarrollador(SingletonLogin.getInstance().getDeveloper().getIdDesarrollador());
        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvActividades.getItems().addAll(listaActividades);
    }

    @Override
    public void btnActividades() {

    }

    @Override
    @FXML
    public void btnCambios() {

    }

    @Override
    @FXML
    public void btnDefectos() {
        SidebarDesarrollador.irMenuDefectos();
    }

    @Override
    @FXML
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
    private void btnVerDetalleActividad() {
        if (esElementoSeleccionado()) {
            Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
            MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLDetalleActividad.fxml", 1000, 600, actividad);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    private boolean esElementoSeleccionado() {
        return tvActividades.getSelectionModel().getSelectedItem() != null;
    }

    @FXML
    private void btnTerminarActivida(ActionEvent event) {
        if(esElementoSeleccionado()){
            Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
            try{
                ActividadDAO.terminarActividad(actividad.getIdActividad());
                Alertas.mostrarAlertaExito();
                formatearTabla();
                llenarTablaActividades();
            } catch (SQLException e){
                Alertas.mostrarAlertaElementoNoSeleccionado();
            }
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }
}
