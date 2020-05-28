package com.infoshareacademy.domain.api;

public class OrganizerJSON {
    private Long id;
    private String designation;

    public Long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setId(Long id) {
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
