package com.example.proyectofinalprogramacion.mapper;

import com.example.proyectofinalprogramacion.dto.GeneroDTO;
import com.example.proyectofinalprogramacion.entity.Genero;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneroMapper {
    public Genero toEntity(GeneroDTO objGeneroDTO) {
        return new Genero(objGeneroDTO.getId_genero(), objGeneroDTO.getNombre());
    }

    public GeneroDTO toDTO(Genero objGenero) {
        return new GeneroDTO(objGenero.getId_genero(), objGenero.getNombre());
    }
}
