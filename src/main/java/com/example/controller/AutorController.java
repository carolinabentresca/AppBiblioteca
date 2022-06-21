package com.example.controller;

import com.example.model.Autor;
import com.example.repository.AutorRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autor")
@CrossOrigin(origins = "http://localhost:8080")
public class AutorController {

    @Autowired
    private AutorRepository repository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Autor>> list() {
        List<Autor> salida = null;
        try {
            salida = repository.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.ok(salida);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@RequestBody Autor autor) {
        Map<String, Object> salida = new HashMap<>();
        try {
            autor.setIdAutor(0);
            Autor obj = repository.save(autor);
        } catch (Exception e) {
            e.getMessage();
        }
        return ResponseEntity.ok(salida);
    }

}
