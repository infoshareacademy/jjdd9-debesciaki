package com.infoshareacademy.parser;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private int id;
    private Place place;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[Z]")
    private LocalDateTime endDate;
    private String name;
    private Url urls;
    private List<Attachment> attachments;
    private String descLong;
    private int categoryId;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[Z]")
    private LocalDateTime startDate;
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