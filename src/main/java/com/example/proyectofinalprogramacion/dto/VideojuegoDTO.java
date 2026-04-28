package com.example.proyectofinalprogramacion.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class VideojuegoDTO {
    private Integer id_videojuego;
    private String titulo;
    private String desarrolladora;
    private LocalDate fechaSalida;
    private Double horasJugadas;
    private Boolean completado;
    private ArrayList<GeneroDTO> generos;
    private ArrayList<PlataformaDTO> plataformas;

    public VideojuegoDTO() {

    }

    public VideojuegoDTO(Integer id_videojuego, String titulo, String desarrolladora, LocalDate fechaSalida, Double horasJugadas, Boolean completado) {
        this.id_videojuego = id_videojuego;
        this.titulo = titulo;
        this.desarrolladora = desarrolladora;
        this.fechaSalida = fechaSalida;
        this.horasJugadas = horasJugadas;
        this.completado = completado;
    }

    public VideojuegoDTO(Integer id_videojuego, String titulo, String desarrolladora, LocalDate fechaSalida, Double horasJugadas, Boolean completado, ArrayList<GeneroDTO> generos, ArrayList<PlataformaDTO> plataformas) {
        this.id_videojuego = id_videojuego;
        this.titulo = titulo;
        this.desarrolladora = desarrolladora;
        this.fechaSalida = fechaSalida;
        this.horasJugadas = horasJugadas;
        this.completado = completado;
        this.generos = generos;
        this.plataformas = plataformas;
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

    public Double getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(Double horasJugadas) {
        this.horasJugadas = horasJugadas;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    public ArrayList<GeneroDTO> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<GeneroDTO> generos) {
        this.generos = generos;
    }

    public ArrayList<PlataformaDTO> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(ArrayList<PlataformaDTO> plataformas) {
        this.plataformas = plataformas;
    }

    @Override
    public String toString() {
        return "VideojuegoDTO{" +
                "id_videojuego=" + id_videojuego +
                ", titulo='" + titulo + '\'' +
                ", desarrolladora='" + desarrolladora + '\'' +
                ", fechaSalida=" + fechaSalida +
                ", horasJuagas=" + horasJugadas +
                ", completado=" + completado +
                '}';
    }
}
