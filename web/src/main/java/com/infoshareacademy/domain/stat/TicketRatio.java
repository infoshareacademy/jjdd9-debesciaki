package com.infoshareacademy.domain.stat;

public class TicketRatio {
    private String eventName;
    private Long redu;
    private Long full;

    public TicketRatio() {
    }

    public TicketRatio(String eventName, Long redu, Long full) {
        this.eventName = eventName;
        this.redu = redu;
        this.full = full;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getRedu() {
        return redu;
    }

    public void setRedu(Long redu) {
        this.redu = redu;
    }

    public Long getFull() {
        return full;
    }

    public void setFull(Long full) {
        this.full = full;
    }
}
