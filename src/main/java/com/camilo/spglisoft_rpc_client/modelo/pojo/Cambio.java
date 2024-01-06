package com.camilo.spglisoft_rpc_client.modelo.pojo;

public class Cambio {
    private int idCambio;
    private String nombre;
    private String descripcion;
    private String estado;
    private String tipoCambio;
    private String fechaInicio;
    private String fechaFin;
    private int esfuerzoMinutos;
    private String nombreDesarrolladorEncargado;
    private String apellidoPaternoDesarrolladorEncargado;
    private String apellidoMaternoDesarrolladorEncargado;
    private String nombreCompletoDesarrollador;

    public Cambio() {
    }

    public String getNombreDesarrolladorEncargado() {
        return nombreDesarrolladorEncargado;
    }

    public void setNombreDesarrolladorEncargado(String nombreDesarrolladorEncargado) {
        this.nombreDesarrolladorEncargado = nombreDesarrolladorEncargado;
    }

    public String getApellidoPaternoDesarrolladorEncargado() {
        return apellidoPaternoDesarrolladorEncargado;
    }

    public void setApellidoPaternoDesarrolladorEncargado(String apellidoPaternoDesarrolladorEncargado) {
        this.apellidoPaternoDesarrolladorEncargado = apellidoPaternoDesarrolladorEncargado;
    }

    public String getApellidoMaternoDesarrolladorEncargado() {
        return apellidoMaternoDesarrolladorEncargado;
    }

    public void setApellidoMaternoDesarrolladorEncargado(String apellidoMaternoDesarrolladorEncargado) {
        this.apellidoMaternoDesarrolladorEncargado = apellidoMaternoDesarrolladorEncargado;
    }

    public String getNombreCompletoDesarrollador() {
        return nombreCompletoDesarrollador;
    }

    public void setNombreCompletoDesarrollador(String nombreCompletoDesarrollador) {
        this.nombreCompletoDesarrollador = nombreCompletoDesarrollador;
    }

    public int getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(int idCambio) {
        this.idCambio = idCambio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
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

    public int getEsfuerzoMinutos() {
        return esfuerzoMinutos;
    }

    public void setEsfuerzoMinutos(int esfuerzoMinutos) {
        this.esfuerzoMinutos = esfuerzoMinutos;
    }
}
