/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.modelo.dao;

import com.camilo.spglisoft_rpc_client.modelo.ConexionBD;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Proyecto;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Representante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camilo
 */
public class ProyectoDAO {
    public static List<Proyecto> obtenerProyectosPorIdRepresentante(int idResponsable) throws SQLException {
        List<Proyecto> proyectosList = new ArrayList<>();
        String query = "SELECT p.id_proyecto, p.nombre_proyecto, p.descripcion, e.estado_proyecto " +
                "AS estado, DATE_FORMAT(p.fecha_inicio, '%d/%m/%Y') as fecha_inicio, " +
                "DATE_FORMAT(p.fecha_fin, '%d/%m/%Y') as fecha_fin, r.nombre, r.apellido_paterno, r.apellido_materno " +
                "FROM proyecto p INNER JOIN estado_proyecto e ON p.estado_proyecto = e.id_estado_proyecto " +
                "INNER JOIN representante_proyecto r ON p.id_representante = r.id_representante " +
                "WHERE p.id_representante = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idResponsable);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Proyecto proyecto = new Proyecto();
            proyecto.setIdProyecto(resultSet.getInt("id_proyecto"));
            proyecto.setNombreProyecto(resultSet.getString("nombre_proyecto"));
            proyecto.setDescripcion(resultSet.getString("descripcion"));
            proyecto.setFechaInicio(resultSet.getString("fecha_inicio"));
            proyecto.setFechaFin(resultSet.getString("fecha_fin"));
            proyecto.setNombreEstado(resultSet.getString("estado"));
            proyecto.setNombreRepresentante(resultSet.getString("nombre"));
            proyecto.setApellidoPaternoRepresentante(resultSet.getString("apellido_paterno"));
            proyecto.setApellidoMaternoRepresentante(resultSet.getString("apellido_materno"));
            proyectosList.add(proyecto);
        }
        conexionBD.close();
        return proyectosList;
    }

    public static Proyecto obtenerProyectoPorIdDesarrollador(int idDesarrollador) throws SQLException {
        String query = "SELECT p.id_proyecto, p.nombre_proyecto, p.descripcion, e.estado_proyecto " +
                "AS estado, DATE_FORMAT(p.fecha_inicio, '%d/%m/%Y') as fecha_inicio, " +
                "DATE_FORMAT(p.fecha_fin, '%d/%m/%Y') as fecha_fin, r.nombre, r.apellido_paterno, r.apellido_materno " +
                "FROM proyecto p INNER JOIN estado_proyecto e ON p.estado_proyecto = e.id_estado_proyecto " +
                "INNER JOIN representante_proyecto r ON p.id_representante = r.id_representante " +
                "INNER JOIN desarrollador d on p.id_proyecto = d.id_proyecto where id_desarrollador = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idDesarrollador);
        ResultSet resultSet = preparedStatement.executeQuery();

        Proyecto proyecto = new Proyecto();
        while (resultSet.next()) {
            proyecto.setIdProyecto(resultSet.getInt("id_proyecto"));
            proyecto.setNombreProyecto(resultSet.getString("nombre_proyecto"));
            proyecto.setDescripcion(resultSet.getString("descripcion"));
            proyecto.setFechaInicio(resultSet.getString("fecha_inicio"));
            proyecto.setFechaFin(resultSet.getString("fecha_fin"));
            proyecto.setNombreEstado(resultSet.getString("estado"));
            proyecto.setNombreRepresentante(resultSet.getString("nombre"));
            proyecto.setApellidoPaternoRepresentante(resultSet.getString("apellido_paterno"));
            proyecto.setApellidoMaternoRepresentante(resultSet.getString("apellido_materno"));
        }
        conexionBD.close();
        return proyecto;
    }

    public static Proyecto obtenerProyecto(Representante representante) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        Proyecto proyecto = new Proyecto();
        if (conexionBD != null) {
            try {
                String query = "SELECT * FROM proyecto WHERE id_representante = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
                preparedStatement.setInt(1, representante.getIdRepresentante());
                ResultSet resultado = preparedStatement.executeQuery();
                if (resultado.next()) {
                    proyecto.setIdProyecto(resultado.getInt("id_proyecto"));
                    proyecto.setNombreProyecto(resultado.getString("nombre_proyecto"));
                    proyecto.setDescripcion(resultado.getString("descripcion"));
                    proyecto.setFechaInicio(resultado.getString("fecha_inicio"));
                    proyecto.setFechaFin(resultado.getString("fecha_fin"));
                    proyecto.setIdEstado(resultado.getInt("estado_proyecto"));
                    proyecto.setIdRepresentante(resultado.getInt("id_representante"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return proyecto;
    }
}
