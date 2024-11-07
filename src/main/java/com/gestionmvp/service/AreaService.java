package com.gestionmvp.service;

import com.gestionmvp.dto.AreaDTO;
import com.gestionmvp.persistence.entity.Area;
import com.gestionmvp.persistence.repository.AreaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<AreaDTO> listarAreas(){
        List<Area> areas = this.areaRepository.findAll();
        return areas.stream()
                .map(AreaDTO::new)
                .collect(Collectors.toList());
    }

    public Area buscarAreaPorId(Long id){
        return this.areaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El area con id: " + id + " no existe." ));
    }

    public Area modificarArea(Long id, Area areaRecibida){
        Area area = this.buscarAreaPorId(id);

        if(area == null){
            throw new NoSuchElementException("No se encontro el id: " + id);
        }

        area.setNombre(areaRecibida.getNombre());
        area.setDescripcion(areaRecibida.getDescripcion());
        area.setEncargado(areaRecibida.getEncargado());
        area.setNumeroEmpleados(areaRecibida.getNumeroEmpleados());
        area.setCorreo(areaRecibida.getCorreo());

        return area;
    }

    public Area guardarArea(Area area){
        return this.areaRepository.save(area);
    }

    public void eliminarArea(Long id){
        this.areaRepository.deleteById(id);
    }
}
