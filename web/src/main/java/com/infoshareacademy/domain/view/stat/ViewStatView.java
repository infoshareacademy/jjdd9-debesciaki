package com.infoshareacademy.domain.view.stat;

import java.time.LocalDate;

public class ViewStatView {
    private String eventName;
    private LocalDate viewDate;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getViewDate() {
        return viewDate;
    }

    public void setViewDate(LocalDate viewDate) {
        this.viewDate = viewDate;
    }
}
