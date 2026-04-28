package com.example.proyectofinalprogramacion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "videojuego")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_videojuego;

    private String titulo;
    private String desarrolladora;
    private LocalDate fechaSalida;
    private Double horasJuagas;
    private Boolean completado;
}
