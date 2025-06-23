package com.mycompany.tpi_programacion.dao;

import com.mycompany.tpi_programacion.db.ConexionDB;
import com.mycompany.tpi_programacion.modelos.Equipo;
import com.mycompany.tpi_programacion.modelos.Partido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidoDAO {

    public void guardar(Partido p, String fase) throws SQLException {
        String sql = """
            INSERT INTO partidos (
              equipo1_id, equipo2_id, puntaje_equipo1, puntaje_equipo2,
              equipo_ganador, nombre_partido, fase)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = ConexionDB.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, p.getEquipo1().getId());
            ps.setInt(2, p.getEquipo2().getId());
            ps.setInt(3, p.getPuntajeEquipo1());
            ps.setInt(4, p.getPuntajeEquipo2());
            ps.setString(5, p.getEquipoGanador());
            ps.setString(6, p.getNombrePartido());
            ps.setString(7, fase);
            ps.executeUpdate();
        }
    }

    public List<Partido> obtenerPorFase(List<Equipo> equipos, String fase) throws SQLException {
        List<Partido> lista = new ArrayList<>();
        String sql = "SELECT * FROM partidos WHERE fase = ?";

        try (Connection c = ConexionDB.getConexion(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fase);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Equipo e1 = buscarEquipoPorId(equipos, rs.getInt("equipo1_id"));
                Equipo e2 = buscarEquipoPorId(equipos, rs.getInt("equipo2_id"));

                Partido p = new Partido(e1, e2, rs.getString("nombre_partido"));
                p.setPuntajeEquipo1(rs.getInt("puntaje_equipo1"));
                p.setPuntajeEquipo2(rs.getInt("puntaje_equipo2"));
                p.setEquipoGanador(rs.getString("equipo_ganador"));
                lista.add(p);
            }
        }

        return lista;
    }

    private Equipo buscarEquipoPorId(List<Equipo> equipos, int id) {
        for (Equipo e : equipos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public void actualizarResultado(Partido partido) throws SQLException {
        String sql = "UPDATE partido SET puntajeEquipo1 = ?, puntajeEquipo2 = ?, equipoGanador = ? WHERE id = ?";
        PreparedStatement stmt = ConexionDB.getConexion().prepareStatement(sql);
        stmt.setInt(1, partido.getPuntajeEquipo1());
        stmt.setInt(2, partido.getPuntajeEquipo2());
        stmt.setString(3, partido.getEquipoGanador());
        stmt.setInt(4, partido.getId());
        stmt.executeUpdate();
    }

}
