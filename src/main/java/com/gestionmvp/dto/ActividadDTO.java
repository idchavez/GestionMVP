package com.gestionmvp.dto;

import com.gestionmvp.persistence.entity.Actividad;
import com.gestionmvp.persistence.entity.EstadoActividadEnum;
import com.gestionmvp.persistence.entity.PrioridadEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActividadDTO {

    private Long id;
    private String nombre;
    private String empleadoAsignado;
    private String descripcion;
    private LocalDateTime creacion;
    private LocalDateTime vencimiento;
    private PrioridadEnum prioridadEnum ;
    private EstadoActividadEnum estadoActividadEnum;

    public ActividadDTO (Actividad actividad){
        this.id = actividad.getId();
        this.nombre = actividad.getNombre();
        this.empleadoAsignado = actividad.getEmpleadoAsignado().getNombres() + " " + actividad.getEmpleadoAsignado().getApellidos();
        this.descripcion = actividad.getDescripcion();
        this.creacion = actividad.getCreacion();
        this.vencimiento = actividad.getVencimiento();
        this.prioridadEnum = actividad.getPrioridadEnum();
        this.estadoActividadEnum = actividad.getEstadoActividadEnum();
    }
}
