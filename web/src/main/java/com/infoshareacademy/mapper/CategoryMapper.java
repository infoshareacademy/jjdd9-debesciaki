package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.CategoryJSON;
import com.infoshareacademy.domain.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CategoryMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(CategoryMapper.class.getName());

    @Inject
    private RootCategoryMapper rootCategoryMapper;

    public Category jsonToDao(CategoryJSON category) {
        Category daoCategory = new Category();
        daoCategory.setApiId(category.getId());
        daoCategory.setName(category.getName());
        STDLOG.info("Success in mapping json to dao");
        return daoCategory;
    }

}
