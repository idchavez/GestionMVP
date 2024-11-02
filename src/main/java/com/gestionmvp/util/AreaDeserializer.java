package com.gestionmvp.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gestionmvp.persistence.entity.Area;
import com.gestionmvp.persistence.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class AreaDeserializer extends JsonDeserializer<Area> {
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Area deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        Long id = jsonParser.getLongValue();
        return areaRepository.findById(id)
                .orElseThrow(()-> new IOException("Area con id " + id +" no encontrado"));
    }
}
