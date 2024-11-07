package com.gestionmvp.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gestionmvp.persistence.entity.Proveedor;
import com.gestionmvp.persistence.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProveedorDeserializer extends JsonDeserializer<Proveedor> {

    @Autowired
    private ProveedorRepository proveedorRepository;
    @Override
    public Proveedor deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Long id = jsonParser.getLongValue();
        Proveedor proveedor = new Proveedor();
        proveedor.setId(id);
        return this.proveedorRepository.findById(id)
                .orElseThrow(()-> new IOException("Proveedor con id " + id +" no encontrado"));
    }
}
