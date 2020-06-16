package com.infoshareacademy.domain.view.stat;

import java.time.LocalDateTime;

public class ViewStatView {
    private String eventName;
    private LocalDateTime viewDate;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getViewDate() {
        return viewDate;
    }

    public void setViewDate(LocalDateTime viewDate) {
        this.viewDate = viewDate;
    }
}
