package com.gestionmvp.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gestionmvp.persistence.entity.Empleado;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmpleadoSerializer extends JsonSerializer<Empleado> {

    @Override
    public void serialize(Empleado empleado, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", empleado.getId());
        jsonGenerator.writeEndObject();
    }
}
