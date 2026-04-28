package com.example.proyectofinalprogramacion.dto;

import java.time.LocalDate;

public class VideojuegoDTO {
    private Integer id_videojuego;

    private String titulo;
    private String desarrolladora;
    private LocalDate fechaSalida;
    private Double horasJuagas;
    private Boolean completado;

    public VideojuegoDTO() {

    }

    public VideojuegoDTO(Integer id_videojuego, String titulo, String desarrolladora, LocalDate fechaSalida, Double horasJuagas, Boolean completado) {
        this.id_videojuego = id_videojuego;
        this.titulo = titulo;
        this.desarrolladora = desarrolladora;
        this.fechaSalida = fechaSalida;
        this.horasJuagas = horasJuagas;
        this.completado = completado;
    }

    public Integer getId_videojuego() {
        return id_videojuego;
    }

    public void setId_videojuego(Integer id_videojuego) {
        this.id_videojuego = id_videojuego;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getHorasJuagas() {
        return horasJuagas;
    }

    public void setHorasJuagas(Double horasJuagas) {
        this.horasJuagas = horasJuagas;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    @Override
    public String toString() {
        return "VideojuegoDTO{" +
                "id_videojuego=" + id_videojuego +
                ", titulo='" + titulo + '\'' +
                ", desarrolladora='" + desarrolladora + '\'' +
                ", fechaSalida=" + fechaSalida +
                ", horasJuagas=" + horasJuagas +
                ", completado=" + completado +
                '}';
    }
}
