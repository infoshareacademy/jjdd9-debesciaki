package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.RootCategoryJSON;
import com.infoshareacademy.entityDomain.Category;

import javax.ejb.Stateless;

@Stateless
public class RootCategoryMapper {

    public RootCategoryJSON daoToJson (Category category) {
        RootCategoryJSON jsonRootCategory = new RootCategoryJSON();
        jsonRootCategory.setId(category.getRootCategory().getId());
        jsonRootCategory.setName(category.getRootCategory().getName());
        return jsonRootCategory;
    }

}
