package com.infoshareacademy.classJSONs;

public class OrganizerJSON {
    private long id;
    private String designation;

    public long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setId(long id) {
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
