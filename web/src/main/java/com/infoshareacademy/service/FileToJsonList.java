package com.infoshareacademy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Stateless
public class FileToJsonList {
    public <E> List<E> fileToJsonList(File file, Class<E> klazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<E> listJSON = mapper.readValue(file, new TypeReference<List<E>>() {
        });
        return listJSON;
    }
}
