package com.example.proyectofinalprogramacion.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String URL = "jdbc:mysql://shuttle.proxy.rlwy.net:42307/proyecto_final_programacion";
    private static final String USER = "root";
    private static final String PASSWORD = "KFqboggCrXFSoqDZZVXGMfyYYDTjRvYJ";

    private static Connection connection = null;

    public static Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }

//    public static Connection connect() {
//        try {
//            return DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error al conectar con la base de datos", e);
//        }
//
//    }
}
