package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.CategoryJSON;
import com.infoshareacademy.domain.api.RootCategoryJSON;
import com.infoshareacademy.domain.entity.Category;
import com.infoshareacademy.domain.entity.RootCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class RootCategoryMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(RootCategoryMapper.class.getName());

    public RootCategoryJSON daoToJson(RootCategory rootCategory) {
        RootCategoryJSON jsonRootCategory = new RootCategoryJSON();
        jsonRootCategory.setId(rootCategory.getApiId());
        jsonRootCategory.setName(rootCategory.getName());
        STDLOG.info("Success in mapping dao to json");
        return jsonRootCategory;
    }

    public RootCategory jsonToDao(RootCategoryJSON rootCategory) {
        RootCategory daocategory = new RootCategory();
        daocategory.setApiId(rootCategory.getId());
        daocategory.setName(rootCategory.getName());

        STDLOG.info("Success in mapping json to dao");
        return daocategory;
    }
}
