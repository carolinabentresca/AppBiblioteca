package com.example.controller;

import com.example.model.Autor;
import com.example.model.Libro;
import com.example.repository.AutorRepository;
import com.example.repository.LibroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/libro")
@CrossOrigin(origins = "http://localhost:8080")
public class LibroController {

    @Autowired
    private LibroRepository repositoryLibro;

    @Autowired
    private AutorRepository repositoryAutor;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Libro>> list() {
        List<Libro> salida = null;
        try {
            salida = repositoryLibro.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.ok(salida);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Libro> save(@RequestBody Libro libro) {
        Optional<Autor> autorOptional = repositoryAutor.findById(libro.getAutor().getIdAutor());
        try {
            if (autorOptional == null) {
                return ResponseEntity.unprocessableEntity().build();
            }
            libro.setAutor(autorOptional.get());
            libro.setIdLibro(0);
            Libro obj = repositoryLibro.save(libro);
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idLibro}")
    public void delete(@PathVariable int idLibro) {
        repositoryLibro.deleteById(idLibro);
    }
}
