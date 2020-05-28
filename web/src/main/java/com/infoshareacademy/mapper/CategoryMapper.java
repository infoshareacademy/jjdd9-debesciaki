package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.CategoryJSON;
import com.infoshareacademy.domain.entity.Category;

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
        if (category.getId() >= 100) {
            jsonCategory.setRootCategory(rootCategoryMapper.daoToJson(category));
        }
        return jsonCategory;
    }

    public Category jsonToDao(CategoryJSON category) {
        Category daoCategory = new Category();
        daoCategory.setId(category.getId());
        daoCategory.setName(category.getName());
        if (category.getId() >= 100) {
            daoCategory.setRootCategory(rootCategoryMapper.jsonToDao(category));
        }
        return daoCategory;
    }

}
