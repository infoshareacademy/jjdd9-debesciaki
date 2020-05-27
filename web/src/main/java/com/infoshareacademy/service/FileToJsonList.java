package com.infoshareacademy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.domain.api.OrganizerJSON;

import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Stateless
public class FileToJsonList {
    public <E> List<E> generic(File file, Class<E> klazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<E> listJSON = mapper.readValue(file, new TypeReference<List<E>>() {
        });
        return listJSON;
    }

    public  List<OrganizerJSON> organizer(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<OrganizerJSON> listJSON = mapper.readValue(file, new TypeReference<List<OrganizerJSON>>() {
        });
        return listJSON;
    }
}
