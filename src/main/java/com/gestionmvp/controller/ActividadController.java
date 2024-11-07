package com.gestionmvp.controller;

import com.gestionmvp.dto.ActividadDTO;
import com.gestionmvp.persistence.entity.Actividad;
import com.gestionmvp.service.ActividadService;
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
public class ActividadController {

    private static final Logger logger = LoggerFactory.getLogger(ActividadController.class);

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping("/actividades")
    public ResponseEntity<List<ActividadDTO>> obtenerActividades(){
        List<ActividadDTO> actividades = this.actividadService.listarActividades();
        if(actividades.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(actividades);
    }

    @PostMapping("/actividades")
    public ResponseEntity<ActividadDTO> agregarActividad(@RequestBody Actividad actividad){
        Actividad nuevaActividad = this.actividadService.guardarActividad(actividad);
        ActividadDTO actividadDTO = new ActividadDTO(nuevaActividad);
        return ResponseEntity.created(URI.create("/actividades/" + actividadDTO.getId())).body(actividadDTO);
    }

    @GetMapping("actividades/{id}")
    public ResponseEntity<ActividadDTO> obtenerActividadPorId(@PathVariable Long id){
        Actividad actividad = this.actividadService.buscarActividadPorId(id);
        if(actividad != null) {
            return ResponseEntity.ok(new ActividadDTO(actividad));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actividades/{id}")
    public ResponseEntity<ActividadDTO> ActualizarActividad(@PathVariable Long id, @RequestBody Actividad actividadRecibida){
        Actividad actividad = this.actividadService.modificarActividad(id, actividadRecibida);
        if(actividad == null) {
            return ResponseEntity.notFound().build();
        }
        this.actividadService.guardarActividad(actividad);
        return ResponseEntity.ok(new ActividadDTO(actividad));
    }

    @DeleteMapping("/actividades/{id}")
    public ResponseEntity<String> eliminarActividad(@PathVariable Long id){
        Actividad actividad = this.actividadService.buscarActividadPorId(id);
        if(actividad == null) {
            return ResponseEntity.notFound().build();
        }
        this.actividadService.eliminarActividad(id);
        return ResponseEntity.ok("Registro eliminado");
    }
}
