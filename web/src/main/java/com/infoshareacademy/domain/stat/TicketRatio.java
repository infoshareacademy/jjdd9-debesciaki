package com.infoshareacademy.domain.stat;

public class TicketRatio {
    private String eventName;
    private Double ratio;

    public TicketRatio() {
    }

    public TicketRatio(String eventName, Double ratio) {
        this.eventName = eventName;
        this.ratio = ratio;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
