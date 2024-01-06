package com.camilo.spglisoft_rpc_client.utils;

import javafx.scene.control.Alert;

public class Alertas {
    static Alert alert;

    public Alertas() {

    }

    public static void mostrarAlertaErrorConexionBD() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error de conexión");
        alert.setContentText("No se pudo establecer conexión con la base de datos, inténtelo de nuevo más tarde");
        alert.showAndWait();
    }

    public static void mostrarAlertaExito() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Operación satisfactoria");
        alert.setContentText("Se ha realizado la operación correctamente");
        alert.showAndWait();
    }

    public static void mostrarAlertaElementoNoSeleccionado() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Selecciona elemento de la tabla");
        alert.setContentText("Debes seleccionar un elemento de la tabla para continuar");
        alert.showAndWait();
    }

    public static void mostrarAlertaLoginFallido() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Credenciales no válidas");
        alert.setContentText("Los datos son incorrectos, intententelo de nuevo");
        alert.showAndWait();
    }
    
    public static void mostrarAlertaCamposFaltantes(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Registro");
        alert.setContentText("Campos faltantes");
        alert.showAndWait();
    }
}
