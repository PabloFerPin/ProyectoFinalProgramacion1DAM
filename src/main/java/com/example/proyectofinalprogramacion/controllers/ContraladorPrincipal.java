package com.example.proyectofinalprogramacion.controllers;

import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.service.VideojuegoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/videojuegos")
public class ContraladorPrincipal {
    private final VideojuegoService service;

    public ContraladorPrincipal(VideojuegoService service) {
        this.service = service;
    }

    @GetMapping
    public ArrayList<VideojuegoDTO> listar() {
        return service.listarVideojuegos();
    }

    @PostMapping
    public VideojuegoDTO insertar(@RequestBody VideojuegoDTO objVideojuegoDTO) {
        service.insertarVideojuego(objVideojuegoDTO);

        return objVideojuegoDTO;
    }
}
