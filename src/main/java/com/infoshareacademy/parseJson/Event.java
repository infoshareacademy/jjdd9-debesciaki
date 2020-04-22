package com.infoshareacademy.parseJson;

import java.util.List;

public class Event {
    private int id;
    private Place place;
    private String endDate;
    private String name;
    private Url urls;
    private List<Attachment> attachments;
    private String descLong;
    private int categoryId;
    private String startDate;
    private Organizer organizer;
    private int active;
    private String descShort;
    private Ticket tickets;

    public int getId() {
        return id;
    }

    public Place getPlace() {
        return place;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }

    public Url getUrls() {
        return urls;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public String getDescLong() {
        return descLong;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getStartDate() {
        return startDate;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public int getActive() {
        return active;
    }

    public String getDescShort() {
        return descShort;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrls(Url urls) {
        this.urls = urls;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void setDescLong(String descLong) {
        this.descLong = descLong;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setDescShort(String descShort) {
        this.descShort = descShort;
    }

    public Ticket getTickets() {
        return tickets;
    }

    public void setTickets(Ticket tickets) {
        this.tickets = tickets;
    }
}