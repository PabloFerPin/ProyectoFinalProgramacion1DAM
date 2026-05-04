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

import java.util.ArrayList;

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
        for (GeneroDTO x : objVideojuegoDTO.getGeneros()) {
            Genero objGenero = gMapper.toEntity(x);
            if (repo.obtenerIdGenero(objGenero) < 1) {
                throw new RuntimeException("Genero no encontrado: " + x.getNombre());
            }
        }

        for (PlataformaDTO y : objVideojuegoDTO.getPlataformas()) {
            Plataforma objPlataforma = pMapper.toEntity(y);
            if (repo.obtenerIdPlataforma(objPlataforma) < 1) {
                throw new RuntimeException("Plataforma no encontrada: " + y.getNombre());
            }
        }

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

    public ArrayList<VideojuegoDTO> listarVideojuegos() {
        ArrayList<VideojuegoDTO> lista = new ArrayList<>();

        for(Videojuego x : repo.obtenerVideojuegos()) {
            ArrayList<GeneroDTO> listaGenerosDTO = new ArrayList<>();
            ArrayList<PlataformaDTO> listaPlataformasDTO = new ArrayList<>();

            for(Genero y : repo.obtenerGenerosDeUnVideojuego(x.getId_videojuego())) {
                listaGenerosDTO.add(gMapper.toDTO(y));
            }

            for(Plataforma y : repo.obtenerPlataformasDeUnVideojuego(x.getId_videojuego())) {
                listaPlataformasDTO.add(pMapper.toDTO(y));
            }

            lista.add(vMapper.toDTO(x, listaGenerosDTO, listaPlataformasDTO));
        }

        return lista;
    }
}
