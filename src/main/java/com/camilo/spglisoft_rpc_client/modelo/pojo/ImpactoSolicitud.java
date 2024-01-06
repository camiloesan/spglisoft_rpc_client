/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.modelo.pojo;

/**
 *
 * @author lecap
 */
public class ImpactoSolicitud {
    
    private String impacto;
    private int idImpacto;

    public ImpactoSolicitud() {
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public int getIdImpacto() {
        return idImpacto;
    }

    public void setIdImpacto(int idImpacto) {
        this.idImpacto = idImpacto;
    }
    
    @Override
    public String toString(){
        return impacto;
    }
}
