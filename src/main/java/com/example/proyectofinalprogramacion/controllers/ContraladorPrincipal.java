package com.example.proyectofinalprogramacion.controllers;

import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.service.VideojuegoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/videojuegos")
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
        return service.insertarVideojuego(objVideojuegoDTO);
    }

    @PutMapping(path = "/{titulo}")
    public VideojuegoDTO modificar(@PathVariable String titulo, @RequestBody VideojuegoDTO objVideojuegoDTO) {
        return service.modificar(titulo, objVideojuegoDTO);
    }

    @DeleteMapping(path = "/{titulo}")
    public VideojuegoDTO eliminar(@PathVariable String titulo) {
        return service.eliminarVideojuego(titulo);
    }
}
