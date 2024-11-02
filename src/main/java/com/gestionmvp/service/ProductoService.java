package com.gestionmvp.service;

import com.gestionmvp.persistence.entity.Producto;
import com.gestionmvp.persistence.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos(){
        return this.productoRepository.findAll();
    }

    public Producto encontrarProductoPorId(Long id){
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El producto con id: " + id + " no existe."));
    }

    public Producto guardarProducto(Producto producto){
        return this.productoRepository.save(producto);
    }

    public void eliminarProducto(Long id){
        this.productoRepository.deleteById(id);
    }
}
