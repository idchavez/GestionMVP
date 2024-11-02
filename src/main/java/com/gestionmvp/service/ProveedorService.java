package com.gestionmvp.service;

import com.gestionmvp.persistence.entity.Proveedor;
import com.gestionmvp.persistence.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository){
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> listarProveedores(){
        return this.proveedorRepository.findAll();
    }

    public Proveedor encontraProveedorPorId(Long id){
        return this.proveedorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El proveedor con id: " + id + " no existe."));
    }

    public Proveedor guardarProveedor(Proveedor proveedor){
        return this.proveedorRepository.save(proveedor);
    }

    public void eliminarProveedor(Long id){
        this.proveedorRepository.deleteById(id);
    }
}
