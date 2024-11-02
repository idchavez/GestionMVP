package com.gestionmvp.controller.dto;

import com.gestionmvp.persistence.entity.Empleado;
import com.gestionmvp.persistence.entity.EstadoLaboralEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpleadoDTO {

    private Long id;
    private String nombres;
    private String apellidos;
    private String cargo;
    private String area;
    private LocalDate fechaIngreso;
    private EstadoLaboralEnum estadoLaboralEnum;
    private String correo;
    private String telefono;

    public EmpleadoDTO(Empleado empleado) {
        this.id = empleado.getId();
        this.nombres = empleado.getNombres();
        this.apellidos = empleado.getApellidos();
        this.cargo = empleado.getCargo().getNombreCargo();
        this.area = empleado.getArea().getNombre();
        this.fechaIngreso = empleado.getFechaIngreso();
        this.estadoLaboralEnum = empleado.getEstadoLaboralEnum();
        this.correo = empleado.getCorreo();
        this.telefono = empleado.getTelefono();
    }

}
