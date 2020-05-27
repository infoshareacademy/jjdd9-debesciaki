package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.CategoryJSON;
import com.infoshareacademy.domain.api.RootCategoryJSON;
import com.infoshareacademy.domain.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class RootCategoryMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(RootCategoryMapper.class.getName());

    public RootCategoryJSON daoToJson(Category category) {
        RootCategoryJSON jsonRootCategory = new RootCategoryJSON();
        jsonRootCategory.setId(category.getRootCategory().getApiId());
        jsonRootCategory.setName(category.getRootCategory().getName());
        STDLOG.info("Success in mapping dao to json");
        return jsonRootCategory;
    }

    public Category jsonToDao(CategoryJSON category) {
        Category daocategory = new Category();
        daocategory.setApiId(category.getRootCategory().getId());
        daocategory.setName(category.getRootCategory().getName());
        STDLOG.info("Success in mapping json to dao");
        return daocategory;
    }
}
