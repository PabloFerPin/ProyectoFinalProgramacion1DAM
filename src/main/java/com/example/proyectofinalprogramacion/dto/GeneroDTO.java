package com.example.proyectofinalprogramacion.dto;

import jakarta.persistence.*;

public class GeneroDTO {
    private int id_genero;
    public String nombre;

    public GeneroDTO() {

    }

    public GeneroDTO(int id_genero, String nombre) {
        this.id_genero = id_genero;
        this.nombre = nombre;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "GeneroDTO{" +
                "id_genero=" + id_genero +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}