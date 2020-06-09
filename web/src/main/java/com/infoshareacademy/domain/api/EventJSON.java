package com.infoshareacademy.domain.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventJSON {
    private Long id;
    private PlaceJSON place;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[Z]")
    private LocalDateTime endDate;
    private String name;
    private UrlJSON urls;
    private List<AttachmentJSON> attachments;
    private String descLong;
    private long categoryId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[Z]")
    private LocalDateTime startDate;
    private OrganizerJSON organizer;
    private int active;
    private String descShort;
    private TicketJSON tickets;

    public Long getId() {
        return id;
    }

    public PlaceJSON getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }

    public UrlJSON getUrls() {
        return urls;
    }

    public List<AttachmentJSON> getAttachments() {
        return attachments;
    }

    public String getDescLong() {
        return descLong;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public OrganizerJSON getOrganizer() {
        return organizer;
    }

    public int getActive() {
        return active;
    }

    public String getDescShort() {
        return descShort;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlace(PlaceJSON place) {
        this.place = place;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrls(UrlJSON urls) {
        this.urls = urls;
    }

    public void setAttachments(List<AttachmentJSON> attachments) {
        this.attachments = attachments;
    }

    public void setDescLong(String descLong) {
        this.descLong = descLong;
    }

    public void setCategoryId(long categoryId) {
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

    public void setOrganizer(OrganizerJSON organizer) {
        this.organizer = organizer;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setDescShort(String descShort) {
        this.descShort = descShort;
    }

    public TicketJSON getTickets() {
        return tickets;
    }

    public void setTickets(TicketJSON tickets) {
        this.tickets = tickets;
    }
}
