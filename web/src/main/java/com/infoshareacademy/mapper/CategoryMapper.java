package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.CategoryJSON;
import com.infoshareacademy.entityDomain.Category;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CategoryMapper {
    @Inject
    RootCategoryMapper rootCategoryMapper;

    public CategoryJSON daoToJson(Category category) {
        CategoryJSON jsonCategory = new CategoryJSON();
        jsonCategory.setId(category.getId());
        jsonCategory.setName(category.getName());
        jsonCategory.setRootCategory(category.getRootCategory());
        return jsonCategory;
    }

    public Category jsonToDao(CategoryJSON category) {
        Category daoCategory = new Category();
        daoCategory.setId(category.getId());
        daoCategory.setName(category.getName());
        daoCategory.setRootCategory(category.getRootCategory());
        return daoCategory;
    }

}
