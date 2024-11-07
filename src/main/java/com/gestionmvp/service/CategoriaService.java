package com.gestionmvp.service;

import com.gestionmvp.persistence.entity.Categoria;
import com.gestionmvp.persistence.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias(){
        return this.categoriaRepository.findAll();
    }

    public Categoria encontrarPorCategoria(Long id){
        return this.categoriaRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("La categoria con id: " + id + " no existe."));
    }

    public Categoria modificarCategoria(Long id, Categoria categoriaRecibida){
        Categoria categoria = this.encontrarPorCategoria(id);

        if(categoria == null){
            throw new NoSuchElementException("No se encontro el id: " + id);
        }

        categoria.setNombreCategoria(categoriaRecibida.getNombreCategoria());
        categoria.setDescripcion(categoriaRecibida.getDescripcion());
        categoria.setEstadoCategoria(categoriaRecibida.isEstadoCategoria());

        return categoria;

    }

    public Categoria guardarCategoria(Categoria categoria){
        return this.categoriaRepository.save(categoria);
    }

    public void eliminarCategoria(Long id){
        this.categoriaRepository.deleteById(id);
    }
}
