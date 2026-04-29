package com.example.proyectofinalprogramacion.service;

import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.entity.Videojuego;
import com.example.proyectofinalprogramacion.mapper.*;
import com.example.proyectofinalprogramacion.repository.VideojuegoDAO;
import org.springframework.stereotype.Service;

@Service
public class VideojuegoService {
    public final VideojuegoDAO vDao;
    public final VideojuegoMapper vMapper;
    public final GeneroMapper gMapper;
    public final PlataformaMapper pMapper;

    public VideojuegoService(VideojuegoDAO vDao, VideojuegoMapper vMapper, GeneroMapper gMapper, PlataformaMapper pMapper) {
        this.vDao = vDao;
        this.vMapper = vMapper;
        this.gMapper = gMapper;
        this.pMapper = pMapper;
    }

    public VideojuegoDTO insertarVideojuego(VideojuegoDTO objVideojuegoDTO) {
        Videojuego objVideojuego = vMapper.toEntity(objVideojuegoDTO);
        //itero el mapper
    }
}
