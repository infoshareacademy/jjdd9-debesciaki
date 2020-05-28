package com.infoshareacademy.domain.api;

public class PlaceJSON {
    private long id;
    private String subname;
    private String name;
    private AddressJSON address;

    public long getId() {
        return id;
    }

    public String getSubname() {
        return subname;
    }

    public void setAddress(AddressJSON address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public AddressJSON getAddress() {
        return address;
    }
}
