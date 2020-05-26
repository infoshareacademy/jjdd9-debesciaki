package com.infoshareacademy.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.classJSONs.OrganizerJSON;
import com.infoshareacademy.mapper.OrganizerMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Stateless
public class OrganizerPersisterBean {
    @Inject
    OrganizerMapper organizerMapper;

    @PersistenceContext
    EntityManager entityManager;

    public void fromJsonFile(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<OrganizerJSON> organizerJSON = mapper.readValue(file, new TypeReference<List<OrganizerJSON>>() {
        });
        for (OrganizerJSON o : organizerJSON) {
            entityManager.persist(organizerMapper.jsonToDao(o));
        }
    }
}
