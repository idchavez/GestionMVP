package com.gestionmvp.service;

import com.gestionmvp.persistence.entity.Cargo;
import com.gestionmvp.persistence.repository.CargoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CargoService {
    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public List<Cargo> listarCargos(){
        return this.cargoRepository.findAll();
    }

    public Cargo encontrarCargoPorId(Long id){
        return this.cargoRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("El cargo con id: " + id + " no existe."));
    }

    public Cargo modificarCargo(Long id, Cargo cargoRecibido){
        Cargo cargo = this.encontrarCargoPorId(id);
        if(cargo == null)
            throw new NoSuchElementException("No se encontro el id: " + id);

        cargo.setNombreCargo(cargoRecibido.getNombreCargo());
        cargo.setDescripcion(cargoRecibido.getDescripcion());
        return cargo;
    }

    public Cargo guardarCargo(Cargo cargo){
        return this.cargoRepository.save(cargo);
    }

    public void eliminarCargo(Long id){
        this.cargoRepository.deleteById(id);
    }
}
