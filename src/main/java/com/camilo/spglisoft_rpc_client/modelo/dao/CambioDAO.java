package com.camilo.spglisoft_rpc_client.modelo.dao;

import com.camilo.spglisoft_rpc_client.modelo.ConexionBD;
import com.camilo.spglisoft_rpc_client.modelo.pojo.Cambio;
import com.camilo.spglisoft_rpc_client.modelo.pojo.TipoCambio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CambioDAO {
    public static List<Cambio> obtenerCambiosPorIdSolicitud(int idSolicitudCambio, int tipoCambio) throws SQLException {
        List<Cambio> listaCambios = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT c.nombre, c.descripcion, d.nombre as nombre_desarrollador, " +
                "d.apellido_paterno, d.apellido_materno, t.tipo_cambio " +
                "FROM cambio c INNER JOIN tipo_cambio t ON c.id_tipo_cambio = t.id_tipo_cambio " +
                "INNER JOIN desarrollador d ON c.id_desarrollador = d.id_desarrollador " +
                "WHERE id_solicitud_cambio = (?) AND c.id_tipo_cambio = (?)";
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idSolicitudCambio);
        preparedStatement.setInt(2, tipoCambio);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Cambio cambio = new Cambio();
            cambio.setNombre(resultSet.getString("nombre"));
            cambio.setDescripcion(resultSet.getString("descripcion"));
            cambio.setTipoCambio(resultSet.getString("tipo_cambio"));
            cambio.setNombreDesarrolladorEncargado(resultSet.getString("nombre_desarrollador"));
            cambio.setApellidoPaternoDesarrolladorEncargado(resultSet.getString("apellido_paterno"));
            cambio.setApellidoMaternoDesarrolladorEncargado(resultSet.getString("apellido_materno"));
            cambio.setNombreCompletoDesarrollador(cambio.getNombreDesarrolladorEncargado()
                + " " + cambio.getApellidoPaternoDesarrolladorEncargado()
                    + " " + cambio.getApellidoMaternoDesarrolladorEncargado());
            listaCambios.add(cambio);
        }
        conexionBD.close();
        return listaCambios;
    }

    public static List<TipoCambio> obtenerTiposCambio() {
        List<TipoCambio> listaTiposCambio = new ArrayList<>();
        try {
            Connection conexionBD = ConexionBD.obtenerConnection();
            String query = "SELECT * FROM tipo_cambio";

            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                TipoCambio tipoCambio = new TipoCambio();
                tipoCambio.setIdCambio(resultSet.getInt("id_tipo_cambio"));
                tipoCambio.setTipoCambio(resultSet.getString("tipo_cambio"));
                listaTiposCambio.add(tipoCambio);
            }
            conexionBD.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaTiposCambio;
    }
}
