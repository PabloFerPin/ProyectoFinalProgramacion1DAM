package com.example.proyectofinalprogramacion.repository;

import com.example.proyectofinalprogramacion.config.ConexionMySQL;
import com.example.proyectofinalprogramacion.entity.Genero;
import com.example.proyectofinalprogramacion.entity.Plataforma;
import com.example.proyectofinalprogramacion.entity.Videojuego;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class VideojuegoDAO {
    public int insertarVideojuego(Videojuego objVideojuego) {
        String sql = "INSERT INTO tabla_videojuegos (titulo, fecha_salida, horas_jugadas, completado, desarrolladora) VALUES (?, ?, ?, ?, ?)";
        int idGenerado = 0;

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pStat.setString(1, objVideojuego.getTitulo());
            pStat.setObject(2, objVideojuego.getFechaSalida());
            pStat.setDouble(3, objVideojuego.getHorasJugadas());
            pStat.setBoolean(4, objVideojuego.getCompletado());
            pStat.setString(5, objVideojuego.getDesarrolladora());

            pStat.executeUpdate();

            ResultSet rs = pStat.getGeneratedKeys();
            while (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGenerado;
    }

//    public int insertarPlataforma(Plataforma objPlataforma) {
//        String SQL = "INSERT IGNORE INTO tabla_plataformas (nombre) VALUES (?)";
//        int idGenerado = 0;
//
//        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            pStat.setString(1, objPlataforma.getNombre());
//
//            pStat.executeUpdate();
//            ResultSet rs = pStat.getGeneratedKeys();
//            while(rs.next()) {
//                idGenerado = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return idGenerado;
//    }

    public int obtenerIdPlataforma(Plataforma objPlataforma) {
        String sql = "SELECT id FROM tabla_plataformas WHERE nombre = ?";
        int id = 0;

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, objPlataforma.getNombre());

            ResultSet rs = pStat.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

//    public int insertarGenero(Genero objGenero) {
//        //GESTIONAR 1 Y 0 DEL ID
//        String SQL = "INSERT IGNORE INTO tabla_generos (nombre) VALUES (?)";
//        int idGenerado = 0;
//
//        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            pStat.setString(1, objGenero.getNombre());
//
//            pStat.executeUpdate();
//            ResultSet rs = pStat.getGeneratedKeys();
//            while(rs.next()) {
//                idGenerado = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return idGenerado;
//    }

    public int obtenerIdGenero(Genero objGenero) {
        //GESTIONAR 1 Y 0 DEL ID
        String sql = "SELECT id FROM tabla_generos WHERE nombre = ?";
        int id = 0;

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, objGenero.getNombre());

            ResultSet rs = pStat.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void insertarVideojuegoGenero(int idVideojuego, int idGenero) {
        String sql = "INSERT INTO tabla_videojuegos_generos (id_videojuego, id_genero) VALUES (?, ?)";

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setInt(1, idVideojuego);
            pStat.setInt(2, idGenero);

            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarVideojuegoPlataforma(int idVideojuego, int idPlataforma) {
        String sql = "INSERT INTO tabla_videojuegos_plataformas (id_videojuego, id_plataforma) VALUES (?, ?)";

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setInt(1, idVideojuego);
            pStat.setInt(2, idPlataforma);

            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}