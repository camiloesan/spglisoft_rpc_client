package com.camilo.spglisoft_rpc_client.modelo.pojo;

public class Actividad {
    private int idActividad;
    private int idProyecto;
    private int idDesarrollador;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private int idEstado;
    private String nombreEstado;
    private int esfuerzoMinutos;
    private String descripcion;
    private String nombreDesarrollador;
    private String apellidoPDesarrollador;
    private String apellidoMDesarrollador;
    private String nombreCompletoDesarrollador;

    public Actividad(int idActividad, int idProyecto, int idDesarrollador, String nombre, String fechaInicio, String fechaFin, int idEstado, String nombreEstado, int esfuerzoMinutos, String descripcion) {
        this.idActividad = idActividad;
        this.idProyecto = idProyecto;
        this.idDesarrollador = idDesarrollador;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
        this.esfuerzoMinutos = esfuerzoMinutos;
        this.descripcion = descripcion;
    }

    public Actividad() {
    }

    public String getNombreCompletoDesarrollador() {
        return nombreCompletoDesarrollador;
    }

    public void setNombreCompletoDesarrollador(String nombreCompletoDesarrollador) {
        this.nombreCompletoDesarrollador = nombreCompletoDesarrollador;
    }

    public String getNombreDesarrollador() {
        return nombreDesarrollador;
    }

    public void setNombreDesarrollador(String nombreDesarrollador) {
        this.nombreDesarrollador = nombreDesarrollador;
    }

    public String getApellidoPDesarrollador() {
        return apellidoPDesarrollador;
    }

    public void setApellidoPDesarrollador(String apellidoPDesarrollador) {
        this.apellidoPDesarrollador = apellidoPDesarrollador;
    }

    public String getApellidoMDesarrollador() {
        return apellidoMDesarrollador;
    }

    public void setApellidoMDesarrollador(String apellidoMDesarrollador) {
        this.apellidoMDesarrollador = apellidoMDesarrollador;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getEsfuerzoMinutos() {
        return esfuerzoMinutos;
    }

    public void setEsfuerzoMinutos(int esfuerzoMinutos) {
        this.esfuerzoMinutos = esfuerzoMinutos;
    }
}
