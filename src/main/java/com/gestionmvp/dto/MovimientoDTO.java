package com.gestionmvp.dto;

import com.gestionmvp.persistence.entity.Movimiento;
import com.gestionmvp.persistence.entity.TipoMovimientoEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimientoDTO {

    private Long id;
    private String productoMovimiento;
    private TipoMovimientoEnum tipoMovimientoEnum;
    private Integer cantidad;
    private String descripcion;
    private LocalDateTime fechaMovimiento;
    private String responsable;

    public MovimientoDTO(Movimiento movimiento){
        this.id = movimiento.getId();
        this.productoMovimiento = movimiento.getProductoMovimiento().getNombreProducto();
        this.tipoMovimientoEnum = movimiento.getTipoMovimientoEnum();
        this.cantidad = movimiento.getCantidad();
        this.descripcion = movimiento.getDescripcion();
        this.fechaMovimiento = movimiento.getFechaMovimiento();
        this.responsable = movimiento.getResponsable().getNombres() + " " + movimiento.getResponsable().getApellidos();
    }
}
