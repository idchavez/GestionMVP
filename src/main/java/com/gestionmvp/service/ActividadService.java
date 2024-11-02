package com.gestionmvp.service;

import com.gestionmvp.persistence.entity.Actividad;
import com.gestionmvp.persistence.repository.ActividadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ActividadService{

    private final ActividadRepository actividadRepository;

    public ActividadService(ActividadRepository actividadRepository){
        this.actividadRepository = actividadRepository;
    }

    public List<Actividad> listarActividades(){
        return this.actividadRepository.findAll();
    }

    public Actividad buscarActividadPorId(Long id){
        return this.actividadRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("La actividad con id: " + id + " no existe." ));
    }

    public Actividad guardarActividad(Actividad actividad){
        return this.actividadRepository.save(actividad);
    }

    public void eliminarActividad(Long id){
        this.actividadRepository.deleteById(id);
    }

}
