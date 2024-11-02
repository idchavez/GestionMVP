package com.gestionmvp.service;

import com.gestionmvp.persistence.entity.Area;
import com.gestionmvp.persistence.repository.AreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Area> listarAreas(){
        return this.areaRepository.findAll();
    }

    public Area buscarAreaPorId(Long id){
        return this.areaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El area con id: " + id + " no existe." ));
    }

    public Area guardarArea(Area area){
        return this.areaRepository.save(area);
    }

    public void eliminarArea(Long id){
        this.areaRepository.deleteById(id);
    }
}
