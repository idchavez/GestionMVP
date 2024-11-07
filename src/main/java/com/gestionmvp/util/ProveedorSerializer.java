package com.gestionmvp.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gestionmvp.persistence.entity.Proveedor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProveedorSerializer extends JsonSerializer<Proveedor> {

    @Override
    public void serialize(Proveedor proveedor, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", proveedor.getId());
        jsonGenerator.writeEndObject();
    }
}
