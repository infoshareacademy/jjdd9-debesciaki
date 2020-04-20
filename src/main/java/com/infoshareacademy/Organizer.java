package com.infoshareacademy;

public class Organizer {
    private int id;
    private String designation;

    Organizer() {
        this.id = 0;
        this.designation = "example";
    }

    Organizer(int id, String designation) {
        this.id = id;
        this.designation = designation;
    }

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
