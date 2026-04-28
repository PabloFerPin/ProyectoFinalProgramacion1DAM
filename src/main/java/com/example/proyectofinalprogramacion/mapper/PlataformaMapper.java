package com.example.proyectofinalprogramacion.mapper;

import com.example.proyectofinalprogramacion.dto.PlataformaDTO;
import com.example.proyectofinalprogramacion.entity.Plataforma;
import org.springframework.stereotype.Component;

@Component
public class PlataformaMapper {
    public Plataforma toEntity(PlataformaDTO objPlataformaDTO) {
        return new Plataforma(objPlataformaDTO.getId_plataforma(), objPlataformaDTO.getNombre());
    }

    public PlataformaDTO toDTO(Plataforma objPlataforma) {
        return new PlataformaDTO(objPlataforma.getId_plataforma(), objPlataforma.getNombre());
    }
}
