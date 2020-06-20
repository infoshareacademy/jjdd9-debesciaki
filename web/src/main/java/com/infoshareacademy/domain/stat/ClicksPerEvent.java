package com.infoshareacademy.domain.stat;

public class ClicksPerEvent {
    private String eventName;
    private Long count;

    public ClicksPerEvent() {
    }

    public ClicksPerEvent(String eventName, Long count) {
        this.eventName = eventName;
        this.count = count;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
