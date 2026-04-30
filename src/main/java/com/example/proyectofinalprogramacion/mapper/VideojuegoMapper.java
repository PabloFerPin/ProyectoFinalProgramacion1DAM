package com.example.proyectofinalprogramacion.mapper;

import com.example.proyectofinalprogramacion.dto.GeneroDTO;
import com.example.proyectofinalprogramacion.dto.PlataformaDTO;
import com.example.proyectofinalprogramacion.entity.Plataforma;
import org.springframework.stereotype.Component;
import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.entity.Videojuego;

import java.util.ArrayList;

@Component
public class VideojuegoMapper {
    public Videojuego toEntity(VideojuegoDTO objVideojuegoDTO) {
        return new Videojuego(objVideojuegoDTO.getId_videojuego(), objVideojuegoDTO.getTitulo(), objVideojuegoDTO.getDesarrolladora(), objVideojuegoDTO.getFechaSalida(), objVideojuegoDTO.getHorasJugadas(), objVideojuegoDTO.getCompletado());
    }

    public VideojuegoDTO toDTO(Videojuego objVideojuego, ArrayList<GeneroDTO> listaGeneros, ArrayList<PlataformaDTO> listaPlataformas) {
        return new VideojuegoDTO(objVideojuego.getId_videojuego(), objVideojuego.getTitulo(), objVideojuego.getDesarrolladora(), objVideojuego.getFechaSalida(), objVideojuego.getHorasJugadas(), objVideojuego.getCompletado(), listaGeneros, listaPlataformas);
    }
}
