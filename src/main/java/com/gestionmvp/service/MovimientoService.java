package com.gestionmvp.service;

import com.gestionmvp.dto.MovimientoDTO;
import com.gestionmvp.persistence.entity.Movimiento;
import com.gestionmvp.persistence.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    public MovimientoService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    public List<MovimientoDTO> listarMovimientos(){
        List<Movimiento> movimientos = this.movimientoRepository.findAll();
        return movimientos.stream()
                .map(MovimientoDTO::new)
                .collect(Collectors.toList());
    }

    public Movimiento encontrarMovimientoPorId(Long id){
        return this.movimientoRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("El movimiento con id: " + id + " no existe."));
    }

    public Movimiento modificarMovimiento(Long id, Movimiento movimientoRecibido) {
        Movimiento movimiento = this.encontrarMovimientoPorId(id);
        if(movimiento == null) {
            throw new NoSuchElementException("No se encontro el id: " + id);
        }
        movimiento.setProductoMovimiento(movimientoRecibido.getProductoMovimiento());
        movimiento.setTipoMovimientoEnum(movimientoRecibido.getTipoMovimientoEnum());
        movimiento.setCantidad(movimientoRecibido.getCantidad());
        movimiento.setDescripcion(movimientoRecibido.getDescripcion());
        movimiento.setFechaMovimiento(movimientoRecibido.getFechaMovimiento());
        movimiento.setResponsable(movimientoRecibido.getResponsable());

        return movimiento;
    }
    public Movimiento guardarMovimiento(Movimiento movimiento){
        return this.movimientoRepository.save(movimiento);
    }

    public void  eliminarMovimiento(Long id){
        this.movimientoRepository.deleteById(id);
    }

}
