package com.gestionmvp.controller;

import com.gestionmvp.dto.AreaDTO;
import com.gestionmvp.persistence.entity.Actividad;
import com.gestionmvp.persistence.entity.Area;
import com.gestionmvp.service.ActividadService;
import com.gestionmvp.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("gestionmvp-app")
@CrossOrigin(value = "http://localhost:4200")
public class AreaController {
    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/areas")
    public ResponseEntity<List<AreaDTO>> obtenerAreas(){
        List<AreaDTO> areas = this.areaService.listarAreas();
        if(areas.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(areas);
        }
    }

    @PostMapping("/areas")
    public ResponseEntity<AreaDTO> agregarArea(@RequestBody Area area){
        Area nuevaArea = this.areaService.guardarArea(area);
        AreaDTO areaDTO = new AreaDTO(nuevaArea);
        return ResponseEntity.created(URI.create("/areas/" + areaDTO.getId())).body(areaDTO);
    }

    @GetMapping("/areas/{id}")
    public ResponseEntity<AreaDTO> obtenerAreaPorId(@PathVariable Long id){
        Area area = this.areaService.buscarAreaPorId(id);
        if(area == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new AreaDTO(area));
        }
    }

    @PutMapping("/areas/{id}")
    public ResponseEntity<AreaDTO> ActualizarArea(@PathVariable Long id, @RequestBody Area areaRecibida){
        Area area = this.areaService.modificarArea(id, areaRecibida);
        if (area == null){
            return ResponseEntity.notFound().build();
        }
        this.areaService.guardarArea(area);
        return ResponseEntity.ok(new AreaDTO(area));
    }

    @DeleteMapping("/areas/{id}")
    public ResponseEntity<String> eliminarArea(@PathVariable Long id){
        Area area = this.areaService.buscarAreaPorId(id);
        if (area == null){
            return ResponseEntity.notFound().build();
        }
        this.areaService.eliminarArea(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}
