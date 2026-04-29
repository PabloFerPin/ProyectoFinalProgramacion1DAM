package com.example.proyectofinalprogramacion.service;

import com.example.proyectofinalprogramacion.dto.GeneroDTO;
import com.example.proyectofinalprogramacion.dto.PlataformaDTO;
import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.entity.Genero;
import com.example.proyectofinalprogramacion.entity.Plataforma;
import com.example.proyectofinalprogramacion.entity.Videojuego;
import com.example.proyectofinalprogramacion.mapper.*;
import com.example.proyectofinalprogramacion.repository.VideojuegoDAO;
import org.springframework.stereotype.Service;

@Service
public class VideojuegoService {
    public final VideojuegoDAO repo;
    public final VideojuegoMapper vMapper;
    public final GeneroMapper gMapper;
    public final PlataformaMapper pMapper;

    public VideojuegoService(VideojuegoDAO repo, VideojuegoMapper vMapper, GeneroMapper gMapper, PlataformaMapper pMapper) {
        this.repo = repo;
        this.vMapper = vMapper;
        this.gMapper = gMapper;
        this.pMapper = pMapper;
    }

    public void insertarVideojuego(VideojuegoDTO objVideojuegoDTO) {
        Videojuego objVideojuego = vMapper.toEntity(objVideojuegoDTO);
        int idVideojuegoNuevo = repo.insertarVideojuego(objVideojuego);

        //itero el mapper para sacra los id e insertar en tabla_videojuego_genero
        for(GeneroDTO x : objVideojuegoDTO.getGeneros()) {
            Genero objGenero = gMapper.toEntity(x);

            int idGenero = repo.obtenerIdGenero(objGenero);

            repo.insertarVideojuegoGenero(idVideojuegoNuevo, idGenero);
        }

        for (PlataformaDTO y : objVideojuegoDTO.getPlataformas()) {
            Plataforma objPlataforma = pMapper.toEntity(y);

            int idPlataforma = repo.obtenerIdPlataforma(objPlataforma);

            repo.insertarVideojuegoPlataforma(idVideojuegoNuevo, idPlataforma);
        }
    }
}
