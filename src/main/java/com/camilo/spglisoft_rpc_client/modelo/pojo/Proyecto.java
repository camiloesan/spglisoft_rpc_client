/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.modelo.pojo;

/**
 *
 * @author camilo
 */
public class Proyecto {
    private int idProyecto;
    private String nombreProyecto;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private int idEstado;
    private String nombreEstado;
    private int idRepresentante;
    private String nombreRepresentante;
    private String apellidoPaternoRepresentante;
    private String apellidoMaternoRepresentante;

    public Proyecto() {
    }

    public Proyecto(int idProyecto, String nombreProyecto, String descripcion, String fechaInicio, String fechaFin, int idEstado, int idRepresentante) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idEstado = idEstado;
        this.idRepresentante = idRepresentante;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getApellidoPaternoRepresentante() {
        return apellidoPaternoRepresentante;
    }

    public void setApellidoPaternoRepresentante(String apellidoPaternoRepresentante) {
        this.apellidoPaternoRepresentante = apellidoPaternoRepresentante;
    }

    public String getApellidoMaternoRepresentante() {
        return apellidoMaternoRepresentante;
    }

    public void setApellidoMaternoRepresentante(String apellidoMaternoRepresentante) {
        this.apellidoMaternoRepresentante = apellidoMaternoRepresentante;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(int idRepresentante) {
        this.idRepresentante = idRepresentante;
    }
}
