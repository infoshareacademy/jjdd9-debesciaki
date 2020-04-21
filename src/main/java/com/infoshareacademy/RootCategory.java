package com.infoshareacademy;


public class RootCategory {
    private int id;
    private String name;

    RootCategory() {
        super();
        this.id = 0;
        this.name = "";
    }

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
}
