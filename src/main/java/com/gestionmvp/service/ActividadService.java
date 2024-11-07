package com.gestionmvp.service;

import com.gestionmvp.dto.ActividadDTO;
import com.gestionmvp.persistence.entity.Actividad;
import com.gestionmvp.persistence.repository.ActividadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ActividadService{

    private final ActividadRepository actividadRepository;

    public ActividadService(ActividadRepository actividadRepository){
        this.actividadRepository = actividadRepository;
    }

    public List<ActividadDTO> listarActividades(){
        List<Actividad> actividades = this.actividadRepository.findAll();
        return actividades.stream()
                .map(ActividadDTO::new)
                .collect(Collectors.toList());
    }

    public Actividad buscarActividadPorId(Long id){
        return this.actividadRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("La actividad con id: " + id + " no existe."));
    }

    public Actividad modificarActividad(Long id, Actividad actividadRecibida){
        Actividad actividad = this.buscarActividadPorId(id);

        if (actividad == null)
            throw new NoSuchElementException("No se encontro el id: " + id);

        actividad.setNombre(actividadRecibida.getNombre());
        actividad.setEmpleadoAsignado(actividadRecibida.getEmpleadoAsignado());
        actividad.setDescripcion(actividadRecibida.getDescripcion());
        actividad.setCreacion(actividadRecibida.getCreacion());
        actividad.setVencimiento(actividadRecibida.getVencimiento());
        actividad.setPrioridadEnum(actividadRecibida.getPrioridadEnum());
        actividad.setEstadoActividadEnum(actividadRecibida.getEstadoActividadEnum());

        return actividad;
    }

    public Actividad guardarActividad(Actividad actividad){
        return this.actividadRepository.save(actividad);
    }

    public void eliminarActividad(Long id){
        this.actividadRepository.deleteById(id);
    }

}
