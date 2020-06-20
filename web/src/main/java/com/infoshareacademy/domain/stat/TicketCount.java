package com.infoshareacademy.domain.stat;

public class TicketCount {
    private String eventName;
    private String ticketType;
    private Long count;

    public TicketCount() {
    }

    public TicketCount(String eventName, String ticketType, Long count) {
        this.eventName = eventName;
        this.ticketType = ticketType;
        this.count = count;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
