package com.example.proyectofinalprogramacion.repository;

import com.example.proyectofinalprogramacion.config.ConexionMySQL;
import com.example.proyectofinalprogramacion.dto.GeneroDTO;
import com.example.proyectofinalprogramacion.dto.PlataformaDTO;
import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.entity.Genero;
import com.example.proyectofinalprogramacion.entity.Plataforma;
import com.example.proyectofinalprogramacion.entity.Videojuego;
import com.example.proyectofinalprogramacion.mapper.GeneroMapper;
import com.example.proyectofinalprogramacion.mapper.PlataformaMapper;
import com.example.proyectofinalprogramacion.mapper.VideojuegoMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Repository
public class VideojuegoDAO {
    public final VideojuegoMapper vMapper;
    public final GeneroMapper gMapper;
    public final PlataformaMapper pMapper;

    public VideojuegoDAO(VideojuegoMapper vMapper, GeneroMapper gMapper, PlataformaMapper pMapper) {
        this.vMapper = vMapper;
        this.gMapper = gMapper;
        this.pMapper = pMapper;
    }

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

    public int obtenerIdPlataforma(Plataforma objPlataforma) {
        String sql = "SELECT id FROM tabla_plataformas WHERE nombre = ?";
        int id = 0;

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, objPlataforma.getNombre());

            try(ResultSet rs = pStat.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public int obtenerIdGenero(Genero objGenero) {
        String sql = "SELECT id FROM tabla_generos WHERE nombre = ?";
        int id = 0;

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, objGenero.getNombre());

            try(ResultSet rs = pStat.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
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

    public ArrayList<VideojuegoDTO> obtenerVideojuegos() {
        String sql = "SELECT v.id, v.titulo, v.desarrolladora, v.fecha_salida, v.horas_jugadas, v.completado,\n" +
                "       GROUP_CONCAT(DISTINCT g.nombre) AS generos,\n" +
                "       GROUP_CONCAT(DISTINCT p.nombre) AS plataformas\n" +
                "FROM tabla_videojuegos v\n" +
                "INNER JOIN tabla_videojuegos_generos vg ON v.id = vg.id_videojuego\n" +
                "INNER JOIN tabla_generos g ON vg.id_genero = g.id\n" +
                "INNER JOIN tabla_videojuegos_plataformas vp ON v.id = vp.id_videojuego\n" +
                "INNER JOIN tabla_plataformas p ON vp.id_plataforma = p.id\n" +
                "GROUP BY v.id";
        ArrayList<VideojuegoDTO> lista = new ArrayList<>();

        try(Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql); ResultSet rs = pStat.executeQuery()) {
            while(rs.next()) {
                String[] listaGeneros = rs.getString("generos").split(",");
                ArrayList<GeneroDTO> listaGeneroDTO = new ArrayList<>();
                for(String x : listaGeneros) {
                    listaGeneroDTO.add(gMapper.toDTO(new Genero(0, x)));
                }

                String[] listaPlataformas = rs.getString("plataformas").split(",");
                ArrayList<PlataformaDTO> listaPlataformaDTO = new ArrayList<>();
                for(String x : listaPlataformas) {
                    listaPlataformaDTO.add(pMapper.toDTO(new Plataforma(0, x)));
                }

                Videojuego objVideojuego = new Videojuego(rs.getInt("id"), rs.getString("titulo"), rs.getString("desarrolladora"), rs.getObject("fecha_salida", LocalDate.class) , rs.getDouble("horas_jugadas"), rs.getBoolean("completado"));
                vMapper.toDTO(objVideojuego, listaGeneroDTO, listaPlataformaDTO);
                lista.add(vMapper.toDTO(objVideojuego, listaGeneroDTO, listaPlataformaDTO));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}