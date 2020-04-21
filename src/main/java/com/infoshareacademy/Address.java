package com.infoshareacademy;

public class Address {
    private String street;
    private String zipcode;
    private String city;
    private double lat;
    private double lng;


    Address() {
        this.street = "ex-street";
        this.zipcode = "XX-XXX";
        this.city = "ex-city";
        this.lat = 00.00;
        this.lng = 00.00;
    }

    Address(String street, String zipcode, String city, double lat, double lng) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.lat = lat;
        this.lng = lng;
    }

    Address(String zipcode, String city, double lat, double lng) {
        this.street = "fill";
        this.zipcode = zipcode;
        this.city = city;
        this.lat = lat;
        this.lng = lng;
    }

    Address(String city, double lat, double lng) {
        this.street = "fill";
        this.zipcode = "fill";
        this.city = city;
        this.lat = lat;
        this.lng = lng;
    }

    Address(double lat, double lng) {
        this.street = "fill";
        this.zipcode = "fill";
        this.city = "fill";
        this.lat = lat;
        this.lng = lng;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
