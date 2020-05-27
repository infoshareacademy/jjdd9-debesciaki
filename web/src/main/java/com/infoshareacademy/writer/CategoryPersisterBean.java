package com.infoshareacademy.writer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.domain.api.CategoryJSON;
import com.infoshareacademy.mapper.CategoryMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Stateless
public class CategoryPersisterBean {
    @Inject
    CategoryMapper categoryMapper;

    @PersistenceContext
    EntityManager entityManager;

    public void fromJsonFile(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<CategoryJSON> categoryList = mapper.readValue(file, new TypeReference<List<CategoryJSON>>() {
        });
        for (CategoryJSON c:categoryList){
            entityManager.persist(categoryMapper.jsonToDao(c));
        }
    }
}
