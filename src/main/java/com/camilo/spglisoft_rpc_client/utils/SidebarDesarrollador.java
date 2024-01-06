package com.camilo.spglisoft_rpc_client.utils;

import javafx.scene.control.Alert;
import com.camilo.spglisoft_rpc_client.controladores.MainStage;

public class SidebarDesarrollador {
    public SidebarDesarrollador() {

    }

    public static void irMenuActividades() {
        MainStage.changeView("FXMLActividadesDesarrollador.fxml", 1000, 600);
    }

    public static void irMenuCambios() {

    }

    public static void irMenuDefectos() {
        MainStage.changeView("FXMLDefectosDesarrollador.fxml", 1000, 600);
    }

    public static void irMenuSolicituesCambio() {
        MainStage.changeView("FXMLSolicitudesCambio.fxml", 1000, 600);

    }

    public static void irMenuInformacionProyecto() {
        MainStage.changeView("FXMLDetalleProyecto.fxml", 1000, 600);
    }

    public static void cerrarSesionDesarrollador() {
        Utilidades.mostrarAlertaSimple("Mensaje",
                "Sesion finalizada", Alert.AlertType.INFORMATION);
        SingletonLogin.cleanDetails();
        MainStage.changeView("FXMLLogin.fxml", 600, 400);
    }
}
