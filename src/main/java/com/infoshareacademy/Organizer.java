package com.infoshareacademy;

public class Organizer {
    private int id;
    private String designation;

    public int getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "id of entity: " + id + " and the name of entity: " + designation;
    }
}
