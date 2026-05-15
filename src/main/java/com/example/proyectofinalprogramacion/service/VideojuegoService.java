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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class VideojuegoService {
    private final VideojuegoDAO repo;
    private final VideojuegoMapper vMapper;
    private final GeneroMapper gMapper;
    private final PlataformaMapper pMapper;

    public VideojuegoService(VideojuegoDAO repo, VideojuegoMapper vMapper, GeneroMapper gMapper, PlataformaMapper pMapper) {
        this.repo = repo;
        this.vMapper = vMapper;
        this.gMapper = gMapper;
        this.pMapper = pMapper;
    }

    public VideojuegoDTO insertarVideojuego(VideojuegoDTO objVideojuegoDTO) {
        Videojuego objVideojuego = vMapper.toEntity(objVideojuegoDTO);
        int idVideojuegoNuevo = repo.insertarVideojuego(objVideojuego);

        for (GeneroDTO x : objVideojuegoDTO.getGenerosDTO()) {
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

        for (Videojuego x : repo.obtenerVideojuegos()) {
            ArrayList<GeneroDTO> listaGenerosDTO = new ArrayList<>();
            ArrayList<PlataformaDTO> listaPlataformasDTO = new ArrayList<>();

            for (Genero y : repo.obtenerGenerosDeUnVideojuego(x.getId_videojuego())) {
                listaGenerosDTO.add(gMapper.toDTO(y));
            }

            for (Plataforma y : repo.obtenerPlataformasDeUnVideojuego(x.getId_videojuego())) {
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

        for (Genero x : repo.obtenerGenerosDeUnVideojuego(id)) {
            listaGenerosDTO.add(gMapper.toDTO(x));
        }

        for (Plataforma x : repo.obtenerPlataformasDeUnVideojuego(id)) {
            listaPlataformasDTO.add(pMapper.toDTO(x));
        }

        return vMapper.toDTO(objVideojuego, listaGenerosDTO, listaPlataformasDTO);
    }

    public ArrayList<GeneroDTO> listarGenerosDisponibles() {
        ArrayList<GeneroDTO> lista = new ArrayList<>();

        for (Genero x : repo.obtenerGenerosDisponibles()) {
            lista.add(gMapper.toDTO(x));
        }

        return lista;
    }

    public ArrayList<PlataformaDTO> listarPlataformasDisponibles() {
        ArrayList<PlataformaDTO> lista = new ArrayList<>();

        for (Plataforma x : repo.obtenerPlataformasDisponibles()) {
            lista.add(pMapper.toDTO(x));
        }

        return lista;
    }

    public VideojuegoDTO modificar(Integer id, VideojuegoDTO objVideojuegoDTO) {
        repo.moficarVideojuego(id, vMapper.toEntity(objVideojuegoDTO));

        repo.eliminarVideojuegoGenero(id);
        repo.eliminarVideojuegoPlataforma(id);

        for (GeneroDTO x : objVideojuegoDTO.getGenerosDTO()) {
            int idGeneroParaInsertar = repo.obtenerIdGenero(gMapper.toEntity(x));
            repo.insertarVideojuegoGenero(id, idGeneroParaInsertar);
        }

        for (PlataformaDTO x : objVideojuegoDTO.getPlataformasDTO()) {
            int idPlataformaParaInsertar = repo.obtenerIdPlataforma(pMapper.toEntity(x));
            repo.insertarVideojuegoPlataforma(id, idPlataformaParaInsertar);
        }

        return objVideojuegoDTO;
    }

    public void eliminarVideojuego(int id) {
        repo.eliminarVideojuego(id);
        repo.eliminarVideojuegoGenero(id);
        repo.eliminarVideojuegoPlataforma(id);
    }

    public void importarArchivoCSV(byte[] archivo) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(archivo)))) {
            String linea = bf.readLine();
            int numLinea = 1;

            while (linea != null) {
                String[] camposDeLaTabla = linea.trim().split("\\|");
                ArrayList<GeneroDTO> listaGenerosDTO = new ArrayList<>();
                ArrayList<PlataformaDTO> listaPlataformasDTO = new ArrayList<>();

                try {
                    Videojuego objVideojuego = new Videojuego(0, camposDeLaTabla[0], camposDeLaTabla[1], LocalDate.parse(camposDeLaTabla[2]), Double.parseDouble(camposDeLaTabla[3]), Boolean.parseBoolean(camposDeLaTabla[4]));
                    for(String x : camposDeLaTabla[5].trim().split(",")) {
                        listaGenerosDTO.add(gMapper.toDTO(new Genero(0, x)));
                    }
                    for(String x : camposDeLaTabla[6].trim().split(",")) {
                        listaPlataformasDTO.add(pMapper.toDTO(new Plataforma(0, x)));
                    }

                    int idVideojuegoAInsertar = repo.insertarVideojuego(objVideojuego);
                    for(GeneroDTO y : listaGenerosDTO) {
                        int idGeneroAInsertar = repo.obtenerIdGenero(gMapper.toEntity(y));
                        repo.insertarVideojuegoGenero(idVideojuegoAInsertar, idGeneroAInsertar);
                    }
                    for(PlataformaDTO y : listaPlataformasDTO) {
                        int idPlataformaAInsertar = repo.obtenerIdPlataforma(pMapper.toEntity(y));
                        repo.insertarVideojuegoPlataforma(idVideojuegoAInsertar, idPlataformaAInsertar);
                    }
                } catch(Exception e) {
                    System.out.println("Error al insertar la linea numero " + numLinea);
                }

                linea = bf.readLine();
                numLinea++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] exportarArchivoCSV() {
        ArrayList<VideojuegoDTO> listaVideojuegosDTO = listarVideojuegos();

        String csv = "";
        for(VideojuegoDTO x : listaVideojuegosDTO) {
            String generosString = "";
            for (GeneroDTO y : x.getGenerosDTO()) {
                generosString += y.getNombre() + ",";
            }
            generosString = generosString.substring(0, generosString.length() - 1);

            String plataformasString = "";
            for (PlataformaDTO y : x.getPlataformasDTO()) {
                plataformasString += y.getNombre() + ",";
            }
            plataformasString = plataformasString.substring(0, plataformasString.length() - 1);

            csv += x.getTitulo() + "|" + x.getDesarrolladora() + "|" + x.getFechaSalida() + "|" + x.getHorasJugadas() + "|" + x.getCompletado() + "|" + generosString + "|" + plataformasString + "\r\n";
        }

        return csv.getBytes();
    }
}