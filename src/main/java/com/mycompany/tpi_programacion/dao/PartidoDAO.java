package com.mycompany.tpi_programacion.dao;

import com.mycompany.tpi_programacion.db.ConexionDB;
import com.mycompany.tpi_programacion.modelos.Partido;
import java.sql.*;

public class PartidoDAO {

    public int guardar(Partido partido, String fase) throws SQLException {
        String sql = """
            INSERT INTO partidos (equipo1, equipo2, puntaje_equipo1, puntaje_equipo2, equipo_ganador, nombre_partido, fase)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = ConexionDB.getConexion();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, partido.getEquipo1().getNombre());
            ps.setString(2, partido.getEquipo2().getNombre());
            ps.setInt(3, partido.getPuntajeEquipo1());
            ps.setInt(4, partido.getPuntajeEquipo2());
            ps.setString(5, partido.getEquipoGanador());
            ps.setString(6, partido.getNombrePartido());
            ps.setString(7, fase);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                partido.setId(idGenerado);
                return idGenerado;
            } else {
                throw new SQLException("No se pudo obtener el ID generado.");
            }
        }
    }

    public void actualizarResultado(Partido partido) throws SQLException {
        String sql = """
            UPDATE partidos
            SET puntaje_equipo1 = ?, puntaje_equipo2 = ?, equipo_ganador = ?
            WHERE id = ?
        """;

        try (Connection c = ConexionDB.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, partido.getPuntajeEquipo1());
            ps.setInt(2, partido.getPuntajeEquipo2());
            ps.setString(3, partido.getEquipoGanador());
            ps.setInt(4, partido.getId());

            ps.executeUpdate();
        }
    }
}
