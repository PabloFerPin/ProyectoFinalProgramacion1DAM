package com.example.proyectofinalprogramacion.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GeneroDTO {
    @JsonIgnore
    private Integer id_genero;
    private String nombre;

    public GeneroDTO() {

    }

    public GeneroDTO(Integer id_genero, String nombre) {
        this.id_genero = id_genero;
        this.nombre = nombre;
    }

    public Integer getId_genero() {
        return id_genero;
    }

    public void setId_genero(Integer id_genero) {
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