package com.gestionmvp.controller;

import com.gestionmvp.dto.MovimientoDTO;
import com.gestionmvp.persistence.entity.Movimiento;
import com.gestionmvp.service.MovimientoService;
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
public class MovimientoController {
    private static final Logger logger = LoggerFactory.getLogger(MovimientoController.class);

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimientoDTO>> obtenerMovimientos(){
        List<MovimientoDTO> movimientos = this.movimientoService.listarMovimientos();
        if (movimientos.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(movimientos);
        }
    }

    @PostMapping("/movimientos")
    public ResponseEntity<MovimientoDTO> agregarMovimiento(@RequestBody Movimiento movimiento){
        Movimiento nuevoMovimiento = this.movimientoService.guardarMovimiento(movimiento);
        MovimientoDTO movimientoDTO = new MovimientoDTO(nuevoMovimiento);
        return ResponseEntity.created(URI.create("/movimientos/" + movimientoDTO.getId())).body(movimientoDTO);
    }

    @GetMapping("/movimientos/{id}")
    public ResponseEntity<MovimientoDTO> obtenerMovimientoPorId(@PathVariable Long id){
        Movimiento movimiento = this.movimientoService.encontrarMovimientoPorId(id);
        if (movimiento == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new MovimientoDTO(movimiento));
        }
    }

    @PutMapping("/movimientos/{id}")
    public ResponseEntity<MovimientoDTO> ActualizarMovimiento(@PathVariable Long id, @RequestBody Movimiento movimientoRecibido){
        Movimiento movimiento = this.movimientoService.modificarMovimiento(id,movimientoRecibido);
        if (movimiento == null){
            return ResponseEntity.notFound().build();
        }
        this.movimientoService.guardarMovimiento(movimiento);
        return ResponseEntity.ok(new MovimientoDTO(movimiento));
    }

    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity<String> eliminarMovimiento(@PathVariable Long id){
        Movimiento movimiento = this.movimientoService.encontrarMovimientoPorId(id);
        if (movimiento == null){
            return ResponseEntity.notFound().build();
        }
        this.movimientoService.eliminarMovimiento(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}
