package com.camilo.spglisoft_rpc_client.utils;

import com.camilo.spglisoft_rpc_client.controladores.MainStage;

public class SidebarRepresentante {
    public static void irMenuActividades() {
        MainStage.changeView("FXMLRPActividades.fxml", 1000, 600);
    }

    public static void irMenuDefectos() {

    }

    public static void irMenuDesarrolladores() {
        MainStage.changeView("FXMLRPDesarrolladores.fxml", 1000, 600);
    }

    public static void irMenuInformacionProyeto() {
        MainStage.changeView("FXMLDetalleProyecto.fxml", 1000, 600);
    }

    public static void irMenuProyectos() {
        MainStage.changeView("FXMLRPMenuPrincipal.fxml", 1000, 600);
    }
    
    public static void irConsultarSolicitudesCambio(){
        MainStage.changeView("FXMLConsultarSolicitudes.fxml", 1000, 600);
    }
}
