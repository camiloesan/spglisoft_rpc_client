package com.camilo.spglisoft_rpc_client.utils;

import javafx.scene.control.Alert;
import com.camilo.spglisoft_rpc_client.controladores.MainStage;

public class SidebarDesarrollador {
    public SidebarDesarrollador() {

    }

    public static void irMenuActividades() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
    }

    public static void irMenuCambios() {

    }

    public static void irMenuDefectos() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLDefectosDesarrollador.fxml", 1000, 600);
    }

    public static void irMenuSolicituesCambio() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLSolicitudesCambio.fxml", 1000, 600);

    }

    public static void irMenuInformacionProyecto() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLDetalleProyecto.fxml", 1000, 600);
    }

    public static void cerrarSesionDesarrollador() {
        Utilidades.mostrarAlertaSimple("Mensaje",
                "Sesion finalizada", Alert.AlertType.INFORMATION);
        SingletonLogin.cleanDetails();
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLLogin.fxml", 600, 400);
    }
}
