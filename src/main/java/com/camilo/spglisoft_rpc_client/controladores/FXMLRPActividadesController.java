package com.camilo.spglisoft_rpc_client.controladores;

import java.io.IOException;

import com.camilo.spglisoft_rpc_client.utils.SidebarRepresentante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.camilo.spglisoft_rpc_client.modelo.dao.ActividadDAO;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Actividad;
import com.camilo.spglisoft_rpc_client.modelo.pojo.EstadoActividad;
import com.camilo.spglisoft_rpc_client.utils.Alertas;
import com.camilo.spglisoft_rpc_client.utils.SingletonLogin;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.camilo.spglisoft_rpc_client.utils.Utilidades;

public class FXMLRPActividadesController implements Initializable, ISidebarRPButtons {
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
    @FXML
    private Button btnAsignarActividad;
    @FXML
    private ComboBox<EstadoActividad> cbFiltroActividades;
    @FXML
    private Button btnEliminarActividad;
    @FXML
    private Button btnDesasignarActividad;
    private ObservableList<EstadoActividad> estadosActividad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        //formatearComboFiltro();
        //cbSeleccionFiltro();
        //llenarTablaActividades();
    }

    public void formatearComboFiltro() {
        estadosActividad = FXCollections.observableArrayList();
        List<EstadoActividad> estados = ActividadDAO.obtenerEstadosActividad();
        estadosActividad.addAll(estados);
        cbFiltroActividades.setItems(estadosActividad);
        cbFiltroActividades.getSelectionModel().select(1);
    }

    private void formatearTabla() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("nombreEstado"));
    }
    
    private void cbSeleccionFiltro() {
        EstadoActividad filtro = cbFiltroActividades.getSelectionModel().getSelectedItem();
        switch (filtro.getEstado()) {
            case "No asignada":
                btnAsignarActividad.setDisable(false);
                btnDesasignarActividad.setDisable(true);
                btnEliminarActividad.setDisable(false);
                break;
            case "Asignada":
                btnAsignarActividad.setDisable(true);
                btnDesasignarActividad.setDisable(false);
                btnEliminarActividad.setDisable(true);
                break;
            case "Concluida":
                btnAsignarActividad.setDisable(true);
                btnDesasignarActividad.setDisable(true);
                btnEliminarActividad.setDisable(true);
                break;
        }
    }

    @FXML
    private void llenarTablaActividades() {
        tvActividades.getItems().clear();
        cbSeleccionFiltro();
        List<Actividad> listaActividades = new ArrayList<>();
        try {
            listaActividades = ActividadDAO
                    .obtenerActividadesPorIdProyecto(SingletonLogin.getInstance().getIdProyectoActual(),
                            cbFiltroActividades.getSelectionModel().getSelectedItem().getIdEstado());
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvActividades.getItems().addAll(listaActividades);
    }

    @Override
    public void btnActividades() {
    }

    @Override
    public void btnDefectos() {
    }

    @Override
    @FXML
    public void btnDesarrolladores() {
        SidebarRepresentante.irMenuDesarrolladores();
    }

    @Override
    @FXML
    public void btnSolicitudesCambio() {
        SidebarRepresentante.irConsultarSolicitudesCambio();
    }

    @Override
    @FXML
    public void btnInformacionProyecto() {
        SidebarRepresentante.irMenuInformacionProyeto();
    }

    @Override
    @FXML
    public void btnRegresar() {
        SidebarRepresentante.irMenuProyectos();
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

    @FXML
    private void btnAsignarActividadEvent() {
        if (esElementoSeleccionado()) {
            Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
            MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLAsignarActividad.fxml", 1000, 600, actividad);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    private boolean esElementoSeleccionado() {
        return tvActividades.getSelectionModel().getSelectedItem() != null;
    }

    private void irSolicitudesCambio() {
        SidebarRepresentante.irConsultarSolicitudesCambio();
    }

    @FXML
    private void btnEliminarActividadEvent(ActionEvent event) {
        if (Utilidades.mostrarAlertaConfirmacion("Confirmación",
                "¿Está seguro de que desea eliminar la actividad?")) {
            if(esElementoSeleccionado()){
                Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
                try{
                    ActividadDAO.eliminarActividad(actividad.getIdActividad());
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

    @FXML
    private void btnDesasignarActividadEvent(ActionEvent event) {
        if(esElementoSeleccionado()){
            Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
            try{
                ActividadDAO.desasignarActividad(actividad.getIdActividad());
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

    @FXML
    private void btnRegistrarActividad(ActionEvent event) {
        irRegistrarActividad();
    }
    
    private void irRegistrarActividad() {
        try {
            FXMLLoader loader = Utilidades.cargarVista("/com/camilo/spglisoft_rpc_client/vistas/FXMLRegistrarActividad.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Registrar actividad");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
            llenarTablaActividades();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error",
                    "No se puede mostrar la ventana", Alert.AlertType.ERROR);
        }
    }
}
