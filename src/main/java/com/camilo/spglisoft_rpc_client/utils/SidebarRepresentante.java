package com.camilo.spglisoft_rpc_client.utils;

import com.camilo.spglisoft_rpc_client.controladores.MainStage;

public class SidebarRepresentante {
    public static void irMenuActividades() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLRPActividades.fxml", 1000, 600);
    }

    public static void irMenuDefectos() {

    }

    public static void irMenuDesarrolladores() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLRPDesarrolladores.fxml", 1000, 600);
    }

    public static void irMenuInformacionProyeto() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLDetalleProyecto.fxml", 1000, 600);
    }

    public static void irMenuProyectos() {
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
    }
    
    public static void irConsultarSolicitudesCambio(){
        MainStage.changeView("/com/camilo/spglisoft_rpc_client/vistas/FXMLConsultarSolicitudes.fxml", 1000, 600);
    }
}
