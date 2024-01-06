/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.camilo.spglisoft_rpc_client.modelo.dao.SolicitudCambioDAO;
import com.camilo.spglisoft_rpc_client.modelo.pojo.SolicitudCambio;
import com.camilo.spglisoft_rpc_client.utils.SidebarDesarrollador;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import com.camilo.spglisoft_rpc_client.utils.Utilidades;

/**
 *
 * @author lecap
 */
public class FXMLSolicitudesCambioController implements Initializable, ISidebarDesarrollador{

    private ObservableList<SolicitudCambio> listaSolicitudes;

    @FXML
    private TableView<SolicitudCambio> tvSolicitudesCambio;
    @FXML
    private TableColumn tcTitulo;
    @FXML
    private TableColumn tcFechaSolicitud;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarDatosLista();
    }

    @Override
    public void btnActividades() {
        SidebarDesarrollador.irMenuActividades();
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
    private void btRegistrarSolicitud(ActionEvent event) {
        try {
            FXMLLoader loader = Utilidades.cargarVista("/com/camilo/spglisoft_rpc_client/vistas/FXMLRegistrarSolicitudCambio.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLRegistrarSolicitudCambioController controlador = loader.getController();
            controlador.iniciarDatos();
            
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Registrar solicitud de cambio");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", "No se puede mostrar la ventana",
                    Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void configurarTabla() {
        tcTitulo.setCellValueFactory(new PropertyValueFactory<>("nombreSolicitud"));
        tcFechaSolicitud.setCellValueFactory(new PropertyValueFactory<>("fechaFormateada"));
    }

    private void cargarDatosLista(){
        try {
            //listaSolicitudes.clear();
            listaSolicitudes = FXCollections.observableArrayList();
            ArrayList<SolicitudCambio> solicitudesBD = SolicitudCambioDAO
                    .obtenerSolicitudesRegistradas(SingletonLogin.getInstance().getDesarrollador().getIdDesarrollador());
            listaSolicitudes.addAll(solicitudesBD);
            tvSolicitudesCambio.setItems(listaSolicitudes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void iniciarDatos() {
        cargarDatosLista();
    }
}
