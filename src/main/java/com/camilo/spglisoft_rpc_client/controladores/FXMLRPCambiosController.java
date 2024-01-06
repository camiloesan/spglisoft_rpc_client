/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.controladores;

import com.camilo.spglisoft_rpc_client.modelo.pojo.Cambio;
import com.camilo.spglisoft_rpc_client.modelo.pojo.SolicitudCambio;
import com.camilo.spglisoft_rpc_client.modelo.pojo.TipoCambio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.camilo.spglisoft_rpc_client.modelo.dao.CambioDAO;
import com.camilo.spglisoft_rpc_client.utils.Alertas;

/*
 * Creador: Camilo Espejo Sánchez.
 * Fecha de creación: Dec 14, 2023.
 * Descripción: Caso de uso: Consultar cambios, muestra los cambios que estan
 * asociados con la solicitud seleccionada.
 */

public class FXMLRPCambiosController implements Initializable {

    @FXML
    private ComboBox<TipoCambio> cbFiltro;
    @FXML
    private TableView<Cambio> tvCambios;
    @FXML
    private TableColumn<Cambio, String> colNombre;
    @FXML
    private TableColumn<Cambio, String> colNombreEncargado;
    @FXML
    private TableColumn<Cambio, String> colTipoCambio;
    @FXML
    private Label lblTitulo;
    private ObservableList<TipoCambio> tipoCambios;
    private SolicitudCambio solicitudActual;

    public static SolicitudCambio SolicitudSeleccionada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarInformacion();
        formatearTabla();
        formatearCombo();
        llenarTablaCambios();
    }

    private void inicializarInformacion() {
        solicitudActual = (SolicitudCambio) MainStage.getUserData();
        SolicitudSeleccionada = solicitudActual;
        lblTitulo.setText("Cambios de la solicitud [" + solicitudActual.getNombreSolicitud() + "]");
    }

    private void formatearTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombreEncargado.setCellValueFactory(new PropertyValueFactory<>("nombreCompletoDesarrollador"));
        colTipoCambio.setCellValueFactory(new PropertyValueFactory<>("tipoCambio"));
    }

    private void formatearCombo() {
        tipoCambios = FXCollections.observableArrayList();
        List<TipoCambio> listaTiposCambio = CambioDAO.obtenerTiposCambio();
        tipoCambios.addAll(listaTiposCambio);
        cbFiltro.setItems(tipoCambios);
        cbFiltro.getSelectionModel().select(1);
    }

    @FXML
    private void llenarTablaCambios() {
        tvCambios.getItems().clear();
        List<Cambio> listaCambios = new ArrayList<>();
        try {
            listaCambios =
                    CambioDAO.obtenerCambiosPorIdSolicitud(solicitudActual.getIdSolicitud(),
                            cbFiltro.getSelectionModel().getSelectedItem().getIdCambio());
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvCambios.getItems().addAll(listaCambios);
    }

    @FXML
    private void btnVerDetalleCambio() {
        Cambio cambio = tvCambios.getSelectionModel().getSelectedItem();
        if (cambio != null) {
            MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLDetalleCambio.fxml", 1000, 600, cambio);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    @FXML
    private void btnRegresar() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLConsultarSolicitudes.fxml", 1000, 600);
    }
}
