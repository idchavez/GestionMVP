package com.gestionmvp.controller;

import com.gestionmvp.persistence.entity.Cargo;
import com.gestionmvp.service.CargoService;
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
public class CargoController {

    private static final Logger logger = LoggerFactory.getLogger(CargoController.class);

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/cargos")
    public ResponseEntity<List<Cargo>> obtenerCargos(){
        List<Cargo> cargos = this.cargoService.listarCargos();
        if (cargos.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(cargos);
    }

    @PostMapping("/cargos")
    public ResponseEntity<Cargo> agregarEmpleado(@RequestBody Cargo cargo){
        Cargo nuevoCargo = this.cargoService.guardarCargo(cargo);
        return ResponseEntity.created(URI.create("/cargos/" + nuevoCargo.getId())).body(nuevoCargo);
    }

    @GetMapping("/cargos/{id}")
    public ResponseEntity<Cargo> buscarCargoPorId(@PathVariable Long id){
        Cargo cargo = this.cargoService.encontrarCargoPorId(id);
        if (cargo != null){
            return  ResponseEntity.ok(cargo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/cargos/{id}")
    public ResponseEntity<Cargo> actualizarCargo(@PathVariable Long id, @RequestBody Cargo cargoRecibido){
        Cargo cargo = this.cargoService.modificarCargo(id,cargoRecibido);
        if(cargo == null){
            return ResponseEntity.notFound().build();
        }
        this.cargoService.guardarCargo(cargo);
        return ResponseEntity.ok(cargo);
    }
    @DeleteMapping("/cargos/{id}")
    public ResponseEntity<String> eliminarCargo(@PathVariable Long id){
        Cargo cargo = this.cargoService.encontrarCargoPorId(id);
        if (cargo == null){
            return ResponseEntity.notFound().build();
        }
        this.cargoService.eliminarCargo(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}
