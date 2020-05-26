package com.infoshareacademy.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.domain.api.PlaceJSON;
import com.infoshareacademy.mapper.PlaceMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PlacePersisterBean {
    @Inject
    PlaceMapper placeMapper;

    @PersistenceContext
    EntityManager entityManager;

    public void fromJsonFile(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<PlaceJSON> placeJSON = mapper.readValue(file, new TypeReference<List<PlaceJSON>>() {
        });
        for (PlaceJSON p : placeJSON) {
            entityManager.persist(placeMapper.jsonToDao(p));
        }
    }
}
