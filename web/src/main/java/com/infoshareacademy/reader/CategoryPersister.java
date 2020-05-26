package com.infoshareacademy.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.classJSONs.CategoryJSON;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Stateless
public class CategoryPersister {
    @Inject
    CategoryMapper
    public void fromJsonFile(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<CategoryJSON> categoryList = mapper.readValue(file, new TypeReference<List<CategoryJSON>>() {
        });
        for (CategoryJSON c:categoryList){

        }
    }
}
