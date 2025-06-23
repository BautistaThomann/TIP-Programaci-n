package com.mycompany.tpi_programacion.dao;

import com.mycompany.tpi_programacion.db.ConexionDB;
import com.mycompany.tpi_programacion.modelos.Equipo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {
    public void guardar(Equipo e) throws SQLException {
        String sql = """
            INSERT INTO equipos (id, nombre, cantidad_jugadores, conferencia, partidos_ganados, partidos_perdidos)
            VALUES (?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
              nombre = VALUES(nombre),
              cantidad_jugadores = VALUES(cantidad_jugadores),
              conferencia = VALUES(conferencia),
              partidos_ganados = VALUES(partidos_ganados),
              partidos_perdidos = VALUES(partidos_perdidos)
        """;
        try (Connection c = ConexionDB.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, e.getId());
            ps.setString(2, e.getNombre());
            ps.setInt(3, e.getCantidadJugadores());
            ps.setString(4, e.getConferencia());
            ps.setInt(5, e.getPartidosGanados());
            ps.setInt(6, e.getPartidosPerdidos());
            ps.executeUpdate();
        }
    }

    public List<Equipo> obtenerTodos() throws SQLException {
        List<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipos";
        try (Connection c = ConexionDB.getConexion();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Equipo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("cantidad_jugadores"),
                    rs.getString("conferencia"),
                    rs.getInt("partidos_ganados"),
                    rs.getInt("partidos_perdidos")
                ));
            }
        }
        return lista;
    }
}
