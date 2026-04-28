package com.example.proyectofinalprogramacion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videojuego {
    private Integer id_videojuego;

    private String titulo;
    private String desarrolladora;
    private LocalDate fechaSalida;
    private Double horasJugadas;
    private Boolean completado;
}
