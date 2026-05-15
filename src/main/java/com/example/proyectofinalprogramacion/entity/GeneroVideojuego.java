package com.example.proyectofinalprogramacion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroVideojuego {
    private Integer id_generoVidejuego;

    private Integer id_videojuego;
    private Integer id_genero;
}
