package com.example.proyectofinalprogramacion.dto;

public class PlataformaDTO {
    private Integer id_plataforma;
    private String nombre;

    public PlataformaDTO() {

    }

    public PlataformaDTO(Integer id_plataforma, String nombre) {
        this.id_plataforma = id_plataforma;
        this.nombre = nombre;
    }

    public Integer getId_plataforma() {
        return id_plataforma;
    }

    public void setId_plataforma(Integer id_plataforma) {
        this.id_plataforma = id_plataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "PlataformaDTO{" +
                "id_plataforma=" + id_plataforma +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
