package com.example.proyectofinalprogramacion.repository;

import com.example.proyectofinalprogramacion.config.ConexionMySQL;
import com.example.proyectofinalprogramacion.entity.Genero;
import com.example.proyectofinalprogramacion.entity.Plataforma;
import com.example.proyectofinalprogramacion.entity.Videojuego;

import java.sql.*;

public class VideojuegoDAO {
    public void insertarVideojuego(Videojuego objVideojuego) {
        String sql = "INSERT INTO tabla_videojuegos (titulo, fecha_salida, horas_jugadas, completado, desarrolladora) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, objVideojuego.getTitulo());
            pStat.setObject(2, objVideojuego.getFechaSalida());
            pStat.setDouble(3, objVideojuego.getHorasJugadas());
            pStat.setBoolean(4, objVideojuego.getCompletado());
            pStat.setString(5, objVideojuego.getDesarrolladora());

            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarPlataforma(Plataforma objPlataforma) {
        String sql = "INSERT IGNORE INTO tabla_plataformas (nombre) VALUES (?)";

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, objPlataforma.getNombre());

            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarGenero(Genero objGenero) {
        String sql = "INSERT IGNORE INTO tabla_generos (nombre) VALUES (?)";

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, objGenero.getNombre());

            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}