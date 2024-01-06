/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.modelo.pojo;

/**
 *
 * @author lecap
 */
public class TipoDefecto {
    
    private int idTipoDefecto;
    private String tipoDefecto;

    public TipoDefecto() {
    }

    public int getIdTipoDefecto() {
        return idTipoDefecto;
    }

    public void setIdTipoDefecto(int idTipoDefecto) {
        this.idTipoDefecto = idTipoDefecto;
    }

    public String getTipoDefecto() {
        return tipoDefecto;
    }

    public void setTipoDefecto(String tipoDefecto) {
        this.tipoDefecto = tipoDefecto;
    }
    
    @Override
    public String toString(){
        return tipoDefecto;
    }
    
}
