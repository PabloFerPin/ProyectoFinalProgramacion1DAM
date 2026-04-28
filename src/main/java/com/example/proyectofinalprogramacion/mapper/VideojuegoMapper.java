package com.example.proyectofinalprogramacion.mapper;

import org.springframework.stereotype.Component;
import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.entity.Videojuego;

@Component
public class VideojuegoMapper {
    public Videojuego toEntity(VideojuegoDTO objVideojuegoDTO) {
        return new Videojuego(objVideojuegoDTO.getId_videojuego(), objVideojuegoDTO.getTitulo(), objVideojuegoDTO.getDesarrolladora(), objVideojuegoDTO.getFechaSalida(), objVideojuegoDTO.getHorasJuagas(), objVideojuegoDTO.getCompletado());
    }

    public VideojuegoDTO toDTO(Videojuego objVideojuego) {
        return new VideojuegoDTO(objVideojuego.getId_videojuego(), objVideojuego.getTitulo(), objVideojuego.getDesarrolladora(), objVideojuego.getFechaSalida(), objVideojuego.getHorasJuagas(), objVideojuego.getCompletado());
    }
}
