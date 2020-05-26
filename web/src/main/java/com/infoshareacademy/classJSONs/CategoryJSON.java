package com.infoshareacademy.classJSONs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryJSON{
    private Long id;
    private String name;
    @JsonProperty("root_category") private RootCategoryJSON rootCategory ;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RootCategoryJSON getRootCategory() {
        return rootCategory;
    }

    public void setRootCategory(RootCategoryJSON rootCategory) {
        this.rootCategory = rootCategory;
    }
}
