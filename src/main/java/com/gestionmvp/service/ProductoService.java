package com.gestionmvp.service;

import com.gestionmvp.dto.ProductoDTO;
import com.gestionmvp.persistence.entity.Producto;
import com.gestionmvp.persistence.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> listarProductos(){
        List<Producto> productos = this.productoRepository.findAll();
        return productos.stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }

    public Producto encontrarProductoPorId(Long id){
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El producto con id: " + id + " no existe."));
    }

    public Producto modificarProducto(Long id, Producto productoRecibido){
        Producto producto = this.encontrarProductoPorId(id);
        if (producto == null){
            throw new NoSuchElementException("No se encontro el id: " + id);
        }
        producto.setNombreProducto(productoRecibido.getNombreProducto());
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setStock(productoRecibido.getStock());
        producto.setProveedor(productoRecibido.getProveedor());
        producto.setEstadoProductoEnum(productoRecibido.getEstadoProductoEnum());
        producto.setCategoriaList(productoRecibido.getCategoriaList());

        return  producto;
    }

    public Producto guardarProducto(Producto producto){
        return this.productoRepository.save(producto);
    }

    public void eliminarProducto(Long id){
        this.productoRepository.deleteById(id);
    }
}
