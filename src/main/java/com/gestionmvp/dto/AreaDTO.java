package com.gestionmvp.dto;

import com.gestionmvp.persistence.entity.Area;
import lombok.Data;

@Data
public class AreaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String encargado;
    private Integer numeroEmpleados;
    private String correo;

    public AreaDTO(Area area){
        this.setId(area.getId());
        this.setNombre(area.getNombre());
        this.setDescripcion(area.getDescripcion());
        this.setEncargado(area.getEncargado().getNombres() + " " + area.getEncargado().getApellidos());
        this.setNumeroEmpleados(area.getNumeroEmpleados());
        this.setCorreo(area.getCorreo());
    }
}
