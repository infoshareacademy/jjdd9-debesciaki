package com.infoshareacademy.domain.api;

public class TicketJSON {
    private String type;
    private Integer startTicket;
    private Integer endTicket;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStartTicket() {
        return startTicket;
    }

    public void setStartTicket(Integer startTicket) {
        this.startTicket = startTicket;
    }

    public Integer getEndTicket() {
        return endTicket;
    }

    public void setEndTicket(Integer endTicket) {
        this.endTicket = endTicket;
    }
}
