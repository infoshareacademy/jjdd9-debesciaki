package com.infoshareacademy.domain;

import java.time.LocalDateTime;

public class ReqMapEventDTO {
    private Long id;

    private String name;

    private LocalDateTime endDate;

    private LocalDateTime startDate;

    private String descLong;

    private Long ticketAmount;

    private String organizerDesignation;

    private String categoryName;

    private String placeName;

    private String placeSubname;

    private String city;

    private String street;

    private String zipCode;

    private String typeOfTicket;

    private String reducedTicket;

    private String normalTicket;

    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getDescLong() {
        return descLong;
    }

    public void setDescLong(String descLong) {
        this.descLong = descLong;
    }

    public Long getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(Long ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getOrganizerDesignation() {
        return organizerDesignation;
    }

    public void setOrganizerDesignation(String organizerDesignation) {
        this.organizerDesignation = organizerDesignation;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceSubname() {
        return placeSubname;
    }

    public void setPlaceSubname(String placeSubname) {
        this.placeSubname = placeSubname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTypeOfTicket() {
        return typeOfTicket;
    }

    public void setTypeOfTicket(String typeOfTicket) {
        this.typeOfTicket = typeOfTicket;
    }

    public String getReducedTicket() {
        return reducedTicket;
    }

    public void setReducedTicket(String reducedTicket) {
        this.reducedTicket = reducedTicket;
    }

    public String getNormalTicket() {
        return normalTicket;
    }

    public void setNormalTicket(String normalTicket) {
        this.normalTicket = normalTicket;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
