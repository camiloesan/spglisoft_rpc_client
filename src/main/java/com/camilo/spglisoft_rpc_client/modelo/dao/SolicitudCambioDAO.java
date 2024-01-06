package com.camilo.spglisoft_rpc_client.modelo.dao;

import com.camilo.spglisoft_rpc_client.modelo.pojo.EstadoSolicitud;
import com.camilo.spglisoft_rpc_client.modelo.pojo.SolicitudCambio;
import com.camilo.spglisoft_rpc_client.modelo.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.camilo.spglisoft_rpc_client.modelo.ResultadoOperacion;

public class SolicitudCambioDAO {
    public static void actualizarEstadoSolicitud(int idEstado, int idSolicitud) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "UPDATE solicitud_cambio " +
                "SET id_estado_solicitud = ? " +
                "WHERE id_solicitud = ?";
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idEstado);
        preparedStatement.setInt(2, idSolicitud);
        preparedStatement.execute();
        conexionBD.close();
    }
    
    public static ResultadoOperacion registrarSolicitud(SolicitudCambio solicitud) throws SQLException{
        ResultadoOperacion resultado = new ResultadoOperacion(true, "Error al registrar la solicitud", -1);
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "INSERT INTO solicitud_cambio (id_proyecto, id_desarrollador, nombre_solicitud, "
                        + "descripcion, fecha_solicitud, accion_propuesta, razon_cambio, impacto_cambio, id_estado_solicitud) "
                        + "VALUES (?,?,?,?,CURDATE(),?,?,?,?)";
                PreparedStatement consulta = conexionBD.prepareStatement(query);
                consulta.setInt(1, solicitud.getIdProyecto());
                consulta.setInt(2, solicitud.getIdDesarrollador());
                consulta.setString(3, solicitud.getNombreSolicitud());
                consulta.setString(4, solicitud.getDescripcion());
                consulta.setString(5, solicitud.getAccionPropuesta());
                consulta.setString(6, solicitud.getRazonCambio());
                consulta.setString(7, solicitud.getImpactoCambio());
                consulta.setInt(8, solicitud.getIdEstadoSolicitud());
                int filasAfectadas = consulta.executeUpdate();
                if (filasAfectadas > 0) {
                    resultado.setError(false);
                    resultado.setMensaje("Solicitud de cambio registrada");
                    resultado.setFilasAfectadas(filasAfectadas);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return resultado;
    }

    public static ArrayList<SolicitudCambio> obtenerSolicitudesRegistradas(int idDesarrollador) throws SQLException {
        ArrayList<SolicitudCambio> solicitudes = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT nombre_solicitud, DATE_FORMAT(fecha_solicitud,'%d/%m/%Y') AS fechaFormateada" +
                        " FROM solicitud_cambio WHERE id_desarrollador = ? ";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(consulta);
                preparedStatement.setInt(1,idDesarrollador);
                ResultSet resultado = preparedStatement.executeQuery();
                while (resultado.next()) {
                    SolicitudCambio solicitud = new SolicitudCambio();
                    solicitud.setNombreSolicitud(resultado.getString("nombre_solicitud"));
                    solicitud.setFechaFormateada(resultado.getString("fechaFormateada"));
                    solicitudes.add(solicitud);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return solicitudes;
    }
    public static ArrayList<SolicitudCambio> obtenerSolicitudes(int idProyecto) throws SQLException {
        ArrayList<SolicitudCambio> solicitudes = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT \n" +
                        "sc.id_solicitud,\n" +
                        "sc.nombre_solicitud,\n" +
                        "sc.descripcion,\n" +
                        "DATE_FORMAT(sc.fecha_solicitud, '%d/%m/%Y') AS fecha_formateada ,\n" +
                        "sc.accion_propuesta,\n" +
                        "sc.razon_cambio,\n" +
                        "sc.impacto_cambio,\n" +
                        "sc.id_estado_solicitud, \n" +
                        "es.estado_solicitud,\n" +
                        "CONCAT(d.nombre, ' ', d.apellido_paterno, ' ', d.apellido_materno) AS nombre_desarrollador,\n " +
                        "p.nombre_proyecto\n " +
                        "FROM solicitud_cambio sc\n " +
                        "INNER JOIN desarrollador d ON sc.id_desarrollador = d.id_desarrollador\n " +
                        "INNER JOIN estado_solicitud es ON sc.id_estado_solicitud = es.id_estado_solicitud\n " +
                        "INNER JOIN proyecto p ON sc.id_proyecto = p.id_proyecto WHERE sc.id_proyecto = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idProyecto);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    SolicitudCambio solicitud = new SolicitudCambio();
                    solicitud.setIdSolicitud(resultado.getInt("sc.id_solicitud"));
                    solicitud.setNombreSolicitud(resultado.getString("sc.nombre_solicitud"));
                    solicitud.setDescripcion(resultado.getString("sc.descripcion"));
                    //solicitud.setFechaSolicitud(resultado.getDate("sc.fecha_solicitud"));
                    solicitud.setAccionPropuesta(resultado.getString("accion_propuesta"));
                    solicitud.setRazonCambio(resultado.getString("sc.razon_cambio"));
                    solicitud.setImpactoCambio(resultado.getString("sc.impacto_cambio"));
                    solicitud.setIdEstadoSolicitud(resultado.getInt("sc.id_estado_solicitud"));
                    solicitud.setEstadoSolicitud(resultado.getString("es.estado_solicitud"));
                    solicitud.setNombreDesarrollador(resultado.getString("nombre_desarrollador"));
                    solicitud.setNombreProyecto(resultado.getString("p.nombre_proyecto"));
                    solicitud.setFechaFormateada(resultado.getString("fecha_formateada"));
                    solicitudes.add(solicitud);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return solicitudes;
    }

    public static List<EstadoSolicitud> obtenerEstadosSolicitud() {
        List<EstadoSolicitud> listaEstadosSolicitud = new ArrayList<>();
        try {
            Connection conexionBD = ConexionBD.obtenerConnection();
            String query = "SELECT * FROM estado_solicitud";

            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
                estadoSolicitud.setIdEstadoSolicitud(resultSet.getInt("id_estado_solicitud"));
                estadoSolicitud.setEstadoSolicitud(resultSet.getString("estado_solicitud"));
                listaEstadosSolicitud.add(estadoSolicitud);
            }
            conexionBD.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaEstadosSolicitud;
    }
}
