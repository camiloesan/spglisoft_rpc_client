/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.utils;

import com.camilo.spglisoft_rpc_client.modelo.pojo.Desarrollador;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Proyecto;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Representante;
import spglisoft.Spglisoft;

/**
 *
 * @author camilo
 */
public class SingletonLogin {
    private static SingletonLogin instance;
    private Desarrollador desarrollador;
    private Representante representante;

    private Spglisoft.DeveloperCredentials developer;
    private Spglisoft.ProjectManagerCredentials projectManager;

    private int idProyectoActual;
    private String tipoUsuario;
    private Proyecto proyectoActual;
    
    public static SingletonLogin getInstance() {
        if (instance == null) {
            instance = new SingletonLogin();
        }
        return instance;
    }

    public Spglisoft.DeveloperCredentials getDeveloper() {
        return developer;
    }

    public Spglisoft.ProjectManagerCredentials getProjectManager() {
        return projectManager;
    }

    public void setDeveloper(Spglisoft.DeveloperCredentials developer) {
        this.developer = developer;
    }

    public void setProjectManager(Spglisoft.ProjectManagerCredentials projectManager) {
        this.projectManager = projectManager;
    }

    public Proyecto getProyectoActual() {
        return proyectoActual;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setProyectoActual(Proyecto proyectoActual) {
        this.proyectoActual = proyectoActual;
    }

    public static void cleanDetails() {
        instance = null;
    }

    public int getIdProyectoActual() {
        return idProyectoActual;
    }

    public void setIdProyectoActual(int idProyectoActual) {
        this.idProyectoActual = idProyectoActual;
    }
    
    public Desarrollador getDesarrollador(){
        return desarrollador;
    }
    
    public void setDesarrollador(Desarrollador desarrollador){
        this.desarrollador = desarrollador;
    }
    
    public Representante getRepresentante(){
        return representante;
    }

    public void setRepresentante(Representante representante){
        this.representante = representante;
    }
}
