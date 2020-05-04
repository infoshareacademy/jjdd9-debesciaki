package com.infoshareacademy.menu;

public enum MenuEventsOptionFilter {
    RETURN("Powrót do głównego menu"),
    FILTER_BEFORE("Filtruj wydarzenia do danego momentu"),
    FILTER_AFTER("Filtruj wydarzenia od danego momentu"),
    FILTER_BETWEEN("Filtruj w przedziale czasowym"),
    RESET("Zresetuj listę");

    private String description;

    MenuEventsOptionFilter(String description) {
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
