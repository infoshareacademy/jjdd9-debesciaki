package com.infoshareacademy.domain.view;

import java.time.LocalDateTime;

public class EventView {
    private Long id;
    private Long apiId;
    private String name;
    private String startDate;
    private LocalDateTime startDateLocal;
    private String endDate;
    private LocalDateTime endDateLocal;
    private String descShort;
    private String descLong;
    private Integer active;
    private String categoryName;
    private String organizerName;
    private String placeName;
    private String placeSubname;
    private String placeCity;
    private String placeZipcode;
    private String placeStreet;
    private String ticket;
    private Integer numberOfTickets;
    private Integer minTicketPrice;
    private Integer maxTicketPrice;
    private String website;
    private String facebook;
    private String fileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescShort() {
        return descShort;
    }

    public void setDescShort(String descShort) {
        this.descShort = descShort;
    }

    public String getDescLong() {
        return descLong;
    }

    public void setDescLong(String descLong) {
        this.descLong = descLong;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public Integer getMinTicketPrice() {
        return minTicketPrice;
    }

    public void setMinTicketPrice(Integer minTicketPrice) {
        this.minTicketPrice = minTicketPrice;
    }

    public Integer getMaxTicketPrice() {
        return maxTicketPrice;
    }

    public void setMaxTicketPrice(Integer maxTicketPrice) {
        this.maxTicketPrice = maxTicketPrice;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public LocalDateTime getStartDateLocal() {
        return startDateLocal;
    }

    public void setStartDateLocal(LocalDateTime startDateLocal) {
        this.startDateLocal = startDateLocal;
    }

    public LocalDateTime getEndDateLocal() {
        return endDateLocal;
    }

    public void setEndDateLocal(LocalDateTime endDateLocal) {
        this.endDateLocal = endDateLocal;
    }

    public String getPlaceCity() {
        return placeCity;
    }

    public void setPlaceCity(String placeCity) {
        this.placeCity = placeCity;
    }

    public String getPlaceZipcode() {
        return placeZipcode;
    }

    public void setPlaceZipcode(String placeZipcode) {
        this.placeZipcode = placeZipcode;
    }

    public String getPlaceStreet() {
        return placeStreet;
    }

    public void setPlaceStreet(String placeStreet) {
        this.placeStreet = placeStreet;
    }
}
