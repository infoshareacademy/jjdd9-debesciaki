package com.infoshareacademy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.domain.api.CategoryJSON;
import com.infoshareacademy.domain.api.EventJSON;
import com.infoshareacademy.domain.api.OrganizerJSON;
import com.infoshareacademy.domain.api.PlaceJSON;

import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Stateless
public class FileToJsonList {

    public List<OrganizerJSON> organizer(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<OrganizerJSON> listJSON = mapper.readValue(file, new TypeReference<List<OrganizerJSON>>() {
        });
        return listJSON;
    }

    public List<CategoryJSON> category(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<CategoryJSON> listJSON = mapper.readValue(file, new TypeReference<List<CategoryJSON>>() {
        });
        return listJSON;
    }

    public List<EventJSON> event(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<EventJSON> listJSON = mapper.readValue(file, new TypeReference<List<EventJSON>>() {
        });
        return listJSON;
    }

    public List<PlaceJSON> place(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<PlaceJSON> listJSON = mapper.readValue(file, new TypeReference<List<PlaceJSON>>() {
        });
        return listJSON;
    }
}
