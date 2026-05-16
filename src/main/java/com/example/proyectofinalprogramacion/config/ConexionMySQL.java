package com.example.proyectofinalprogramacion.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_final_programacion";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";  

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }
}
