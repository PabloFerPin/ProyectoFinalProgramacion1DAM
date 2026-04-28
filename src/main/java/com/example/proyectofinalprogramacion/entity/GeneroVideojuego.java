package com.example.proyectofinalprogramacion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genero_videojuego")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroVideojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_generoVidejuego;

    private Integer id_videojuego;
    private Integer id_genero;
}
