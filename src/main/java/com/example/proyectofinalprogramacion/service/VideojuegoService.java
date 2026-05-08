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

    public VideojuegoDTO insertarVideojuego(VideojuegoDTO objVideojuegoDTO) {
//        for (GeneroDTO x : objVideojuegoDTO.getGeneros()) {
//            Genero objGenero = gMapper.toEntity(x);
//            if (repo.obtenerIdGenero(objGenero) < 1) {
//                throw new RuntimeException("Genero no encontrado: " + x.getNombre());
//            }
//        }
//
//        for (PlataformaDTO y : objVideojuegoDTO.getPlataformas()) {
//            Plataforma objPlataforma = pMapper.toEntity(y);
//            if (repo.obtenerIdPlataforma(objPlataforma) < 1) {
//                throw new RuntimeException("Plataforma no encontrada: " + y.getNombre());
//            }
//        }

        Videojuego objVideojuego = vMapper.toEntity(objVideojuegoDTO);
        int idVideojuegoNuevo = repo.insertarVideojuego(objVideojuego);

        for(GeneroDTO x : objVideojuegoDTO.getGenerosDTO()) {
            int idGenero = repo.obtenerIdGenero(gMapper.toEntity(x));
            repo.insertarVideojuegoGenero(idVideojuegoNuevo, idGenero);
        }

        for (PlataformaDTO x : objVideojuegoDTO.getPlataformasDTO()) {
            int idPlataforma = repo.obtenerIdPlataforma(pMapper.toEntity(x));
            repo.insertarVideojuegoPlataforma(idVideojuegoNuevo, idPlataforma);
        }

        return objVideojuegoDTO;
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

    public VideojuegoDTO listarVideojuegoPorId(Integer id) {
        Videojuego objVideojuego = repo.obtenerVideojuegoPorId(id);
        ArrayList<GeneroDTO> listaGenerosDTO = new ArrayList<>();
        ArrayList<PlataformaDTO> listaPlataformasDTO = new ArrayList<>();

        for(Genero x : repo.obtenerGenerosDeUnVideojuego(id)) {
            listaGenerosDTO.add(gMapper.toDTO(x));
        }

        for(Plataforma x : repo.obtenerPlataformasDeUnVideojuego(id)) {
            listaPlataformasDTO.add(pMapper.toDTO(x));
        }

        return vMapper.toDTO(objVideojuego, listaGenerosDTO, listaPlataformasDTO);
    }

    public ArrayList<GeneroDTO> listarGenerosDisponibles() {
        ArrayList<GeneroDTO> lista = new ArrayList<>();

        for(Genero x : repo.obtenerGenerosDisponibles()) {
            lista.add(gMapper.toDTO(x));
        }

        return lista;
    }

    public ArrayList<PlataformaDTO> listarPlataformasDisponibles() {
        ArrayList<PlataformaDTO> lista = new ArrayList<>();

        for(Plataforma x : repo.obtenerPlataformasDisponibles()) {
            lista.add(pMapper.toDTO(x));
        }

        return lista;
    }

    public VideojuegoDTO modificar(Integer id, VideojuegoDTO objVideojuegoDTO) {
        repo.moficarVideojuego(id, vMapper.toEntity(objVideojuegoDTO));

        repo.eliminarVideojuegoGenero(id);
        repo.eliminarVideojuegoPlataforma(id);

        for(GeneroDTO x : objVideojuegoDTO.getGenerosDTO()) {
            int idGeneroParaInsertar = repo.obtenerIdGenero(gMapper.toEntity(x));
            repo.insertarVideojuegoGenero(id, idGeneroParaInsertar);
        }

        for(PlataformaDTO x : objVideojuegoDTO.getPlataformasDTO()) {
            int idPlataformaParaInsertar = repo.obtenerIdPlataforma(pMapper.toEntity(x));
            repo.insertarVideojuegoPlataforma(id, idPlataformaParaInsertar);
        }

        return objVideojuegoDTO;
    }

    public void eliminarVideojuego(String titulo) {
        int idVideojuegoAEliminar = repo.obtenerIdVideojuego(titulo);

        repo.eliminarVideojuego(titulo);
        repo.eliminarVideojuegoGenero(idVideojuegoAEliminar);
        repo.eliminarVideojuegoPlataforma(idVideojuegoAEliminar);
    }
}