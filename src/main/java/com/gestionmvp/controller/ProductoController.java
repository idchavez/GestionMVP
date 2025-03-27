package com.gestionmvp.controller;



import com.gestionmvp.dto.ProductoDTO;
import com.gestionmvp.persistence.entity.Producto;
import com.gestionmvp.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("gestionmvp-app")
@CrossOrigin(value = "http://localhost:8080")
public class ProductoController {
    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> obtenerProductos(){
        List<ProductoDTO> productos = this.productoService.listarProductos();
        if (productos.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(productos);
        }
    }

    @PostMapping("/productos")
    public ResponseEntity<ProductoDTO> agregarProducto(@RequestBody Producto producto){
        Producto nuevoProducto = this.productoService.guardarProducto(producto);
        ProductoDTO productoDTO = new ProductoDTO(nuevoProducto);
        return ResponseEntity.created(URI.create("/productos/" + productoDTO.getId())).body(productoDTO);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id){
        Producto producto = this.productoService.encontrarProductoPorId(id);
        if (producto == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new ProductoDTO(producto));
        }
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> ActualizarProducto(@PathVariable Long id, @RequestBody Producto productoRecibido){
        Producto producto = this.productoService.modificarProducto(id,productoRecibido);
        if (producto == null){
            return ResponseEntity.notFound().build();
        }
        this.productoService.guardarProducto(producto);
        return ResponseEntity.ok(new ProductoDTO(producto));
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        Producto producto = this.productoService.encontrarProductoPorId(id);
        if (producto == null){
            return ResponseEntity.notFound().build();
        }
        this.productoService.eliminarProducto(id);
        return  ResponseEntity.ok("Resgistro eliminado");
    }
}
