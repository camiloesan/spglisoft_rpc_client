/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camilo.spglisoft_rpc_client.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.camilo.spglisoft_rpc_client.modelo.pojo.Defecto;
import com.camilo.spglisoft_rpc_client.modelo.pojo.TipoDefecto;
import com.camilo.spglisoft_rpc_client.modelo.ConexionBD;
import com.camilo.spglisoft_rpc_client.modelo.ResultadoOperacion;

/**
 *
 * @author lecap
 */
public class DefectoDAO {
    public static ResultadoOperacion registrarNuevoDefecto(Defecto defecto) throws SQLException {
        ResultadoOperacion resultado = new ResultadoOperacion(true, "Error al registrar el defecto", -1);
        Connection conexionBD = ConexionBD.obtenerConnection();
        int estadoDefecto = 1;
        if (conexionBD != null) {
            try {
                String query = "INSERT INTO defecto (id_proyecto, id_desarrollador, nombre_defecto, descripcion, "
                        + "fecha_reporte, id_tipo_defecto, id_estado_defecto, esfuerzo_estimado) "
                        + "VALUES (?,?,?,?,CURDATE(),?,?,?)";
                PreparedStatement prepararConsulta = conexionBD.prepareCall(query);
                prepararConsulta.setInt(1, defecto.getIdProyecto());
                prepararConsulta.setInt(2, defecto.getIdDesarrollador());
                prepararConsulta.setString(3, defecto.getNombreDefectoString());
                prepararConsulta.setString(4, defecto.getDescripcion());
                prepararConsulta.setInt(5, defecto.getTipoDefecto());
                prepararConsulta.setInt(6, estadoDefecto);
                prepararConsulta.setInt(7, defecto.getEsfuerzoEstimado());
                int filasAfectadas = prepararConsulta.executeUpdate();
                if (filasAfectadas > 0) {
                    resultado.setError(false);
                    resultado.setMensaje("Defecto registrado");
                    resultado.setFilasAfectadas(filasAfectadas);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                conexionBD.close();
            }
        }
        return resultado;
    }
    
    public static List<TipoDefecto> obtenerTiposDefecto() throws SQLException {
        List<TipoDefecto> tiposDefecto = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "SELECT * FROM tipo_defecto";
                PreparedStatement prepararSentencia =conexionBD.prepareStatement(query);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    TipoDefecto tipoDefecto = new TipoDefecto();
                    tipoDefecto.setIdTipoDefecto(resultado.getInt("id_tipo_defecto"));
                    tipoDefecto.setTipoDefecto(resultado.getString("tipo_defecto"));
                    tiposDefecto.add(tipoDefecto);
                }
            } catch (SQLException e) {
                throw e;
            }
        }
        return tiposDefecto;
    }

    public static List<Defecto> obtenerDefectosRegistradosPorDesarrollador(int idDesarrollador) throws SQLException {
        List<Defecto> listaDefectos = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT d.id_proyecto, d.id_desarrollador, d.nombre_defecto, d.descripcion, " +
                "DATE_FORMAT(d.fecha_reporte, '%d/%m/%Y') AS fecha_reporte, " +
                "d.id_tipo_defecto, d.id_estado_defecto, d.esfuerzo_estimado, t.tipo_defecto, e.estado_defecto " +
                "FROM defecto d INNER JOIN tipo_defecto t on d.id_tipo_defecto = t.id_tipo_defecto " +
                "INNER JOIN estado_defecto e on d.id_estado_defecto = e.id_estado_defecto " +
                "WHERE d.id_desarrollador = (?)";

        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idDesarrollador);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Defecto defecto = new Defecto();
            defecto.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            defecto.setNombreDefectoString(resultSet.getString("nombre_defecto"));
            defecto.setDescripcion(resultSet.getString("descripcion"));
            defecto.setFechaReporte(resultSet.getString("fecha_reporte"));
            defecto.setTipoDefecto(resultSet.getInt("id_tipo_defecto"));
            defecto.setEstadoDefecto(resultSet.getInt("id_estado_defecto"));
            defecto.setEsfuerzoEstimado(resultSet.getInt("esfuerzo_estimado"));
            defecto.setNombreTipoDefecto(resultSet.getString("tipo_defecto"));
            defecto.setNombreEstadoDefecto(resultSet.getString("estado_defecto"));
            listaDefectos.add(defecto);
        }
        conexionBD.close();

        return listaDefectos;
    }
}
