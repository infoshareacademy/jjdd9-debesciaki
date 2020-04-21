package com.infoshareacademy;

import java.util.List;

public class Categories {
    private int id;
    private String name;
    private Root_category root_category;

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

    public Root_category getRoot_category() {
        return root_category;
    }

    public void setRoot_category(Root_category root_category) {
        this.root_category = root_category;
    }
}
