package com.gestionmvp.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gestionmvp.persistence.entity.Producto;
import com.gestionmvp.persistence.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductoDeserializer extends JsonDeserializer<Producto> {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Long id = jsonParser.getLongValue();
        Producto producto = new Producto();
        producto.setId(id);
        return this.productoRepository.findById(id)
                .orElseThrow(()-> new IOException("Producto con id " + id +" no encontrado"));
    }
}
