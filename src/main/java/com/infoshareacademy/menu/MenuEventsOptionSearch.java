package com.infoshareacademy.menu;

public enum MenuEventsOptionSearch {
    RETURN("Powrót do głównego menu"),
    SEARCH_BY_NAME("Wyszukuj po nazwie"),
    SEARCH_BY_ORGANIZER("Wyszukuj po organizatorze"),
    RESET("Zresetuj listę");

    private String description;

    MenuEventsOptionSearch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
