package com.example.proyectofinalprogramacion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plataforma_videojuego")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlataformaVidejuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_plataformaVideojuego;


    private Integer id_genero;
    private Integer id_plataforma;
}
