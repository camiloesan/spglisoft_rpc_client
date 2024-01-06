package com.camilo.spglisoft_rpc_client.modelo.pojo;

public class EstadoSolicitud {
    private int idEstadoSolicitud;
    private String estadoSolicitud;

    public EstadoSolicitud(int idEstadoSolicitud, String estadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
        this.estadoSolicitud = estadoSolicitud;
    }

    public EstadoSolicitud() {
    }

    public int getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setIdEstadoSolicitud(int idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    @Override
    public String toString() {
        return estadoSolicitud;
    }
}
