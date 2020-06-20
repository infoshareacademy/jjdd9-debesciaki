package com.infoshareacademy.domain.stat;

public class ClicksPerOrganizer {
    private String organizerName;
    private Long count;

    public ClicksPerOrganizer() {
    }

    public ClicksPerOrganizer(String organizerName, Long count) {
        this.organizerName = organizerName;
        this.count = count;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
