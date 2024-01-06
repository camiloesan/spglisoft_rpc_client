package com.camilo.spglisoft_rpc_client.modelo.pojo;

public class TipoCambio {
    private int idCambio;
    private String tipoCambio;

    public TipoCambio() {
    }

    public TipoCambio(int idCambio, String tipoCambio) {
        this.idCambio = idCambio;
        this.tipoCambio = tipoCambio;
    }

    public int getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(int idCambio) {
        this.idCambio = idCambio;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    @Override
    public String toString(){
        return tipoCambio;
    }
}
