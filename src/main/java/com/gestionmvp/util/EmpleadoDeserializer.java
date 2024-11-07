package com.gestionmvp.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gestionmvp.persistence.entity.Empleado;
import com.gestionmvp.persistence.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmpleadoDeserializer extends JsonDeserializer<Empleado> {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Long id = jsonParser.getLongValue();
        Empleado empleado = new Empleado();
        empleado.setId(id);
        return empleado;
//        return this.empleadoRepository.findById(id)
//                .orElseThrow(()-> new IOException("Empleado con id " + id +" no encontrado"));
    }
}
