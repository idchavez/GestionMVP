package com.gestionmvp.controller;

import com.gestionmvp.persistence.entity.Categoria;
import com.gestionmvp.service.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("gestionmvp-app")
@CrossOrigin(value = "http://localhost:4200")
public class CategoriaController {
    private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> obtenerCategorias(){
        List<Categoria> categorias = this.categoriaService.listarCategorias();
        if(categorias.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(categorias);
        }
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> agregarCategoria(@RequestBody Categoria categoria){
        Categoria nuevaCategoria = this.categoriaService.guardarCategoria(categoria);
        return ResponseEntity.created(URI.create("/categorias/" + nuevaCategoria.getId())).body(nuevaCategoria);
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id){
        Categoria categoria = this.categoriaService.encontrarPorCategoria(id);
        if(categoria == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categoria);
        }
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> ActualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaRecibida){
        Categoria categoria = this.categoriaService.modificarCategoria(id,categoriaRecibida);
        if(categoria == null){
            return  ResponseEntity.notFound().build();
        }
        this.categoriaService.guardarCategoria(categoria);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id){
        Categoria categoria = this.categoriaService.encontrarPorCategoria(id);
        if(categoria == null){
            return ResponseEntity.notFound().build();
        }
        this.categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}
