package com.gestionmvp.controller;

import com.gestionmvp.dto.EmpleadoDTO;
import com.gestionmvp.persistence.entity.Empleado;
import com.gestionmvp.service.EmpleadoService;
import com.inventarios.exception.RecursoNoEncontradoExcepcion;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("gestionmvp-app")
@CrossOrigin(value = "http://localhost:4200")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    private static final Logger logger =
            LoggerFactory.getLogger(EmpleadoController.class);

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/empleados")
    public List<EmpleadoDTO> obtenerEmpleados(){
        List<EmpleadoDTO> empleados = empleadoService.listarEmpleados();
        logger.info("Empleados obtenidos: ");
        empleados.forEach((empleado) -> logger.info(empleado.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public EmpleadoDTO agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar: "+ empleado);
        Empleado empleadoagregado = this.empleadoService.guardarEmpleado(empleado);
        return new EmpleadoDTO(empleadoagregado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<EmpleadoDTO> buscarEmpleadoPorId(@PathVariable Long id){
        Empleado empleado = this.empleadoService.encontrarEmpleadoPorId(id);
        if(empleado != null){
            return  ResponseEntity.ok(new EmpleadoDTO(empleado));
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
    }

//    @PutMapping("/empleados/{id}")
//    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable long id, @RequestBody Empleado empleadoRecibido){
//
//        Empleado empleado = this.empleadoService.encontrarEmpleadoPorId(id);
//        logger.info("Empleado encontrado: " + empleado.getId());
//
//        //Cargo cargo = this.cargoService.encontrarCargoPorId(empleadoRecibido.getCargo().getId());
//        //logger.info("Cargo id recibido: " + cargo.getId());
//
//        //Area area = this.areaService.buscarAreaPorId(empleadoRecibido.getArea().getId());
//
//        if(empleado == null)
//            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
//        empleado.setNombres(empleadoRecibido.getNombres());
//        empleado.setApellidos(empleadoRecibido.getApellidos());
//        empleado.setCargo(empleadoRecibido.getCargo());
//        empleado.setArea(empleadoRecibido.getArea());
//        empleado.setFechaIngreso(empleadoRecibido.getFechaIngreso());
//        empleado.setEstadoLaboralEnum(empleadoRecibido.getEstadoLaboralEnum());
//        empleado.setCorreo(empleadoRecibido.getCorreo());
//        empleado.setTelefono(empleadoRecibido.getTelefono());
//        logger.info("Empleado a guardar: " + empleado.getNombres());
//        this.empleadoService.guardarEmpleado(empleado);
//        return ResponseEntity.ok(new EmpleadoDTO(empleado));
//    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoRecibido){
        Empleado empleado = this.empleadoService.modificarEmpleado(id,empleadoRecibido);
        this.empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.ok(new EmpleadoDTO(empleado));
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
        Empleado empleado = empleadoService.encontrarEmpleadoPorId(id);
        if (empleado == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        this.empleadoService.eliminarEmpleado(empleado.getId());
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return  ResponseEntity.ok(respuesta);
    }

}
