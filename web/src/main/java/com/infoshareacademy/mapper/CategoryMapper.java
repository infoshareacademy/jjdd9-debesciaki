package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.CategoryJSON;
import com.infoshareacademy.entityDomain.Category;

import javax.ejb.Stateless;

@Stateless
public class CategoryMapper {

    public CategoryJSON daoToJson(Category category) {
        CategoryJSON jsonCategory = new CategoryJSON();
        jsonCategory.setId(category.getId());
        jsonCategory.setName(category.getName());
        jsonCategory.setRootCategory(categoryMapper.daoToJson(category.getRootCategory()));
        return jsonCategory;
    }

    public Category jsonToDao(Category category) {
        Category daoCategory = new Category();
        daoCategory.setId(category.getId());
        daoCategory.setName(category.getName());
        daoCategory.setRootCategory(categoryMapper.daoToJson(category.getRootCategory()));
        return daoCategory;
    }

}
