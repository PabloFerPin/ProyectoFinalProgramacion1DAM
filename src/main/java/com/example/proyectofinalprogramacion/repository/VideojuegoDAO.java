package com.example.proyectofinalprogramacion.repository;

import com.example.proyectofinalprogramacion.config.ConexionMySQL;
import com.example.proyectofinalprogramacion.entity.Genero;
import com.example.proyectofinalprogramacion.entity.Plataforma;
import com.example.proyectofinalprogramacion.entity.Videojuego;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

            try(ResultSet rs = pStat.getGeneratedKeys()) {
                while (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
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

    public int obtenerIdVideojuego(String titulo) {
        String sql = "SELECT id FROM tabla_videojuegos WHERE titulo = ?";
        int id = 0;

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, titulo);

            try (ResultSet rs = pStat.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public int obtenerIdPlataforma(Plataforma objPlataforma) {
        String sql = "SELECT id FROM tabla_plataformas WHERE nombre = ?";
        int id = 0;

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, objPlataforma.getNombre());

            try (ResultSet rs = pStat.executeQuery()) {
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

            try (ResultSet rs = pStat.executeQuery()) {
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

    /*
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
     */

    public ArrayList<Videojuego> obtenerVideojuegos() {
        String sql = "SELECT * FROM tabla_videojuegos";
        ArrayList<Videojuego> lista = new ArrayList<>();

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql); ResultSet rs = pStat.executeQuery()) {
            while (rs.next()) {
                lista.add(new Videojuego(rs.getInt("id"), rs.getString("titulo"), rs.getString("desarrolladora"), rs.getObject("fecha_salida", LocalDate.class), rs.getDouble("horas_jugadas"), rs.getBoolean("completado")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Videojuego obtenerVideojuegoPorId(Integer id) {
        String sql = "SELECT * FROM tabla_videojuegos WHERE id = ?";
        Videojuego objVideojuego = null;

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setInt(1, id);

            try(ResultSet rs = pStat.executeQuery()) {
                while (rs.next()) {
                    objVideojuego = new Videojuego(rs.getInt("id"), rs.getString("titulo"), rs.getString("desarrolladora"), rs.getObject("fecha_salida", LocalDate.class), rs.getDouble("horas_jugadas"), rs.getBoolean("completado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objVideojuego;
    }

    public ArrayList<Genero> obtenerGenerosDeUnVideojuego(int idvideojuego) {
        String sql = "SELECT g.id, g.nombre FROM tabla_generos g INNER JOIN tabla_videojuegos_generos vg ON g.id = vg.id_genero WHERE vg.id_videojuego = ?";
        ArrayList<Genero> lista = new ArrayList<>();

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setInt(1, idvideojuego);

            try (ResultSet rs = pStat.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Genero(rs.getInt("id"), rs.getString("nombre")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Plataforma> obtenerPlataformasDeUnVideojuego(int idvideojuego) {
        String sql = "SELECT p.id, p.nombre FROM tabla_plataformas p INNER JOIN tabla_videojuegos_plataformas vp ON p.id = vp.id_plataforma WHERE vp.id_videojuego = ?";
        ArrayList<Plataforma> lista = new ArrayList<>();

        try (Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setInt(1, idvideojuego);

            try (ResultSet rs = pStat.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Plataforma(rs.getInt("id"), rs.getString("nombre")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Genero> obtenerGenerosDisponibles() {
        String sql = "SELECT * FROM tabla_generos";
        ArrayList<Genero> lista = new ArrayList<>();

        try(Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql); ResultSet rs = pStat.executeQuery()) {
            while(rs.next()) {
                lista.add(new Genero(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Plataforma> obtenerPlataformasDisponibles() {
        String sql = "SELECT * FROM tabla_plataformas";
        ArrayList<Plataforma> lista = new ArrayList<>();

        try(Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql); ResultSet rs = pStat.executeQuery()) {
            while(rs.next()) {
                lista.add(new Plataforma(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void moficarVideojuego(Integer id, Videojuego objVideojuego) {
        String sql = "UPDATE tabla_videojuegos SET titulo = ?, fecha_salida = ?, horas_jugadas = ?, completado = ?, desarrolladora = ? WHERE id = ?";

        try(Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, objVideojuego.getTitulo());
            pStat.setObject(2, objVideojuego.getFechaSalida());
            pStat.setDouble(3, objVideojuego.getHorasJugadas());
            pStat.setBoolean(4, objVideojuego.getCompletado());
            pStat.setString(5, objVideojuego.getDesarrolladora());
            pStat.setInt(6, id);

            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarVideojuego(String titulo) {
        String sql = "DELETE FROM tabla_videojuegos WHERE titulo = ?";

        try(Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setString(1, titulo);

            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarVideojuegoGenero(int id_videojuego) {
        String sql = "DELETE FROM tabla_videojuegos_generos WHERE id_videojuego = ?";

        try(Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setInt(1, id_videojuego);

            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarVideojuegoPlataforma(int id_videojuego) {
        String sql = "DELETE FROM tabla_videojuegos_plataformas WHERE id_videojuego = ?";

        try(Connection conn = ConexionMySQL.connect(); PreparedStatement pStat = conn.prepareStatement(sql)) {
            pStat.setInt(1, id_videojuego);

            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}