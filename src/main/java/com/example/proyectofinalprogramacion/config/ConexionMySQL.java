package com.example.proyectofinalprogramacion.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private final String URL = "jdbc:mysql://shuttle.proxy.rlwy.net:42307/proyecto_final_programacion";
    private final String USER = "root";
    private final String PASSWORD = "KFqboggCrXFSoqDZZVXGMfyYYDTjRvYJ";

    public Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }

    }
}
