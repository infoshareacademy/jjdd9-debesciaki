package com.infoshareacademy.parseJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    private int id;
    private String name;
    @JsonProperty("root_category") private RootCategory rootCategory ;//= new RootCategory();

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
