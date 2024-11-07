package com.gestionmvp.controller;

import com.gestionmvp.persistence.entity.Proveedor;
import com.gestionmvp.service.ProveedorService;
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
public class ProveedorController {

    public static final Logger logger = LoggerFactory.getLogger(ProveedorController.class);
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/proveedores")
    public ResponseEntity<List<Proveedor>> obtenerProveedores(){
        List<Proveedor> proveedores = this.proveedorService.listarProveedores();
        if(proveedores.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping("/proveedores")
    public ResponseEntity<Proveedor> agregarProveedor(@RequestBody Proveedor proveedor){
        Proveedor nuevoProveedor = this.proveedorService.guardarProveedor(proveedor);
        return  ResponseEntity.created(URI.create("/proveedores/" + nuevoProveedor.getId())).body(nuevoProveedor);
    }

    @GetMapping("/proveedores/{id}")
    public ResponseEntity<Proveedor> encontrarProveedorPorId(@PathVariable Long id){
        Proveedor proveedor = this.proveedorService.encontrarProveedorPorId(id);
        if (proveedor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @PutMapping("/proveedores/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorRecibido){
        Proveedor proveedor = this.proveedorService.modificarProveedor(id,proveedorRecibido);
        if(proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        this.proveedorService.guardarProveedor(proveedor);
        return ResponseEntity.ok(proveedor);
    }

    @DeleteMapping("/proveedores/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Long id){
        Proveedor proveedor = this.proveedorService.encontrarProveedorPorId(id);
        if(proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        this.proveedorService.eliminarProveedor(proveedor.getId());
        return  ResponseEntity.ok("Registro eliminado");
    }


}
