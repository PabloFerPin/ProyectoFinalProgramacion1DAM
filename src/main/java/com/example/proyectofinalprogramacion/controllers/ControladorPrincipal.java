package com.example.proyectofinalprogramacion.controllers;

import com.example.proyectofinalprogramacion.dto.GeneroDTO;
import com.example.proyectofinalprogramacion.dto.PlataformaDTO;
import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.service.VideojuegoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/videojuegos")
public class ControladorPrincipal {
    private final VideojuegoService service;

    public ControladorPrincipal(VideojuegoService service) {
        this.service = service;
    }

    @GetMapping
    public ArrayList<VideojuegoDTO> listar() {
        return service.listarVideojuegos();
    }

    @GetMapping(path = "/generos")
    public ArrayList<GeneroDTO> listarGeneros() {
        return service.listarGenerosDisponibles();
    }

    @GetMapping(path = "/plataformas")
    public ArrayList<PlataformaDTO> listarPlataformas() {
        return service.listarPlataformasDisponibles();
    }

    @GetMapping(path = "/{id}")
    public VideojuegoDTO listarUnVideojuego(@PathVariable Integer id) {
        return service.listarVideojuegoPorId(id);
    }

    @GetMapping(path = "/downloadCSV")
    public byte[] exportarCSV() {
        return service.exportarArchivoCSV();
    }

    @PostMapping
    public VideojuegoDTO insertar(@RequestBody VideojuegoDTO objVideojuegoDTO) {
        return service.insertarVideojuego(objVideojuegoDTO);
    }

    @PostMapping(path = "/csv")
    public void importarCSV(@RequestBody byte[] archivo) {
        service.importarArchivoCSV(archivo);
    }

    @PutMapping(path = "/{id}")
    public VideojuegoDTO modificar(@PathVariable Integer id, @RequestBody VideojuegoDTO objVideojuegoDTO) {
        return service.modificar(id, objVideojuegoDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminarVideojuego(id);
    }
}