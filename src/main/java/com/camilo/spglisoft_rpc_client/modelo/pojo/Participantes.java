/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.modelo.pojo;

/**
 *
 * @author lecap
 */
public class Participantes {
    private int idParticipante;
    private int idUsuario;
    private String nombreProyecto;

    public Participantes() {
    }

    public Participantes(int idParticipante, int idUsuario, String nombreProyecto) {
        this.idParticipante = idParticipante;
        this.idUsuario = idUsuario;
        this.nombreProyecto = nombreProyecto;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }
    
    
}
