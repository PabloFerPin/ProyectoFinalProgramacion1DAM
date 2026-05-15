package com.example.proyectofinalprogramacion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genero {
    private Integer id_genero;

    public String nombre;
}
