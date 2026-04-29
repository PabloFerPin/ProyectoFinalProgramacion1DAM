package com.example.proyectofinalprogramacion;

import com.example.proyectofinalprogramacion.entity.Genero;
import com.example.proyectofinalprogramacion.entity.Videojuego;
import com.example.proyectofinalprogramacion.repository.VideojuegoDAO;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        VideojuegoDAO vDao = new VideojuegoDAO();

//        int i = vDao.insertarVideojuego(new Videojuego(1, "test1Videojuego2", "Mi primo", LocalDate.of(2020, 3, 20), 20.2, false));
//        System.out.println(i);

//        vDao.insertarGenero(new Genero(1, "Mundo abierto"));
    }
}
