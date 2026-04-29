package com.example.proyectofinalprogramacion.controllers;

import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.service.VideojuegoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/videojuegos")
public class ContraladorPrincipal {
    private final VideojuegoService service;

    public ContraladorPrincipal(VideojuegoService service) {
        this.service = service;
    }

//    @GetMapping
//    public ArrayList<VideojuegosDTO> listar() {
//
//    }

    @PostMapping
    public VideojuegoDTO insertar(@RequestBody VideojuegoDTO objVideojuegoDTO) {
        service.insertarVideojuego(objVideojuegoDTO);

        return objVideojuegoDTO;
    }
}
