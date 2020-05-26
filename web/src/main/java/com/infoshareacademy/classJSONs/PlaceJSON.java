package com.infoshareacademy.classJSONs;

import com.infoshareacademy.parser.Address;

public class PlaceJSON {
    private int id;
    private String subname;
    private String name;
    private Address address;

    public int getId() {
        return id;
    }

    public String getSubname() {
        return subname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public Address getAddress() {
        return address;
    }
}
