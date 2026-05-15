package com.example.proyectofinalprogramacion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlataformaVidejuego {
    private Integer id_plataformaVideojuego;

    private Integer id_genero;
    private Integer id_plataforma;
}
