package com.infoshareacademy.domain.view.stat.chart;

public class ClicksPerEvent {
    private String eventName;
    private Long count;

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
