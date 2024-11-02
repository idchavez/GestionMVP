package com.gestionmvp.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gestionmvp.persistence.entity.Cargo;
import com.gestionmvp.persistence.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CargoDeserializer extends JsonDeserializer<Cargo> {
    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public Cargo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Long id = jsonParser.getLongValue();
        return cargoRepository.findById(id)
                .orElseThrow(()-> new IOException("Cargo con id " + id +" no encontrado"));
    }
}
