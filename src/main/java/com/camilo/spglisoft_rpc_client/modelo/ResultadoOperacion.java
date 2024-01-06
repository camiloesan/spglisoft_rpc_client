/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.modelo;

/**
 *
 * @author lecap
 */
public class ResultadoOperacion {
    private boolean error;
    private String mensaje;
    private int filasAfectadas;

    public ResultadoOperacion(boolean error, String mensaje, int filasAfectadas) {
        this.error = error;
        this.mensaje = mensaje;
        this.filasAfectadas = filasAfectadas;
    }

    public ResultadoOperacion() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getFilasAfectadas() {
        return filasAfectadas;
    }

    public void setFilasAfectadas(int filasAfectadas) {
        this.filasAfectadas = filasAfectadas;
    }
    
    
}
