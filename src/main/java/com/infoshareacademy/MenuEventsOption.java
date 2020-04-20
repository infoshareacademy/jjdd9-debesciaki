package com.infoshareacademy;

public enum MenuEventsOption {
    RETURN("Powrót"),
    FILTER("Filtruj"),
    SEARCH("Wyszukaj");

    private String description;

    MenuEventsOption(String description) {
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

