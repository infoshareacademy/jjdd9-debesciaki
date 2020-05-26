package com.infoshareacademy.classJSONs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.infoshareacademy.parser.RootCategory;

public class CategoryJSON {
    private Long id;
    private String name;
    @JsonProperty("root_category") private RootCategory rootCategory ;
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

    public RootCategory getRootCategory() {
        return rootCategory;
    }

    public void setRootCategory(RootCategory rootCategory) {
        this.rootCategory = rootCategory;
    }
}
