package com.gestionmvp.service;

import com.gestionmvp.persistence.entity.Movimiento;
import com.gestionmvp.persistence.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    public MovimientoService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    public List<Movimiento> listarMovimientos(){
        return this.movimientoRepository.findAll();
    }

    public Movimiento encontrarMovimientoPorId(Long id){
        return this.movimientoRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("El movimiento con id: " + id + " no existe."));
    }

    public Movimiento guardarMovimiento(Movimiento movimiento){
        return this.movimientoRepository.save(movimiento);
    }

    public void  eliminarMovimiento(Long id){
        this.movimientoRepository.deleteById(id);
    }

}
