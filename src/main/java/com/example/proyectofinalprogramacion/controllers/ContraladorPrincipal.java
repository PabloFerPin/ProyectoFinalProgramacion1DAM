package com.example.proyectofinalprogramacion.controllers;

import com.example.proyectofinalprogramacion.dto.GeneroDTO;
import com.example.proyectofinalprogramacion.dto.PlataformaDTO;
import com.example.proyectofinalprogramacion.dto.VideojuegoDTO;
import com.example.proyectofinalprogramacion.service.VideojuegoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

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

    @DeleteMapping(path = "/{titulo}")
    public void eliminar(@PathVariable String titulo) {
        service.eliminarVideojuego(titulo);
    }
}