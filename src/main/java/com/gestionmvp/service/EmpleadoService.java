package com.gestionmvp.service;

import com.gestionmvp.controller.dto.EmpleadoDTO;
import com.gestionmvp.persistence.entity.Empleado;
import com.gestionmvp.persistence.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /*public List<Empleado> listarEmpleados(){
        return this.empleadoRepository.findAll();
    }*/

    public List<EmpleadoDTO> listarEmpleados(){
        List<Empleado> empleados = this.empleadoRepository.findAll();
        return empleados.stream()
                .map(EmpleadoDTO::new)
                .collect(Collectors.toList());
    }

    public Empleado encontrarEmpleadoPorId(Long id){
        Empleado empleado = this.empleadoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El empleado con id: " + id + " no existe."));
        return empleado;
    }

    public Empleado modificarEmpleado(long id, Empleado empleadoRecibido){

        Empleado empleado = this.encontrarEmpleadoPorId(id);

        if(empleado == null)
            throw new com.inventarios.exception.RecursoNoEncontradoExcepcion("No se encontro el id: " + id);

        empleado.setNombres(empleadoRecibido.getNombres());
        empleado.setApellidos(empleadoRecibido.getApellidos());
        empleado.setCargo(empleadoRecibido.getCargo());
        empleado.setArea(empleadoRecibido.getArea());
        empleado.setFechaIngreso(empleadoRecibido.getFechaIngreso());
        empleado.setEstadoLaboralEnum(empleadoRecibido.getEstadoLaboralEnum());
        empleado.setCorreo(empleadoRecibido.getCorreo());
        empleado.setTelefono(empleadoRecibido.getTelefono());

        return empleado;
    }

    public Empleado guardarEmpleado(Empleado empleado){
        return this.empleadoRepository.save(empleado);
    }

    public void eliminarEmpleado(Long id){
        this.empleadoRepository.deleteById(id);
    }
}
