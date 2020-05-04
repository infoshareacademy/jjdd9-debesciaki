package com.infoshareacademy.menu;

public enum MenuEventsOption {
    RETURN("Powrót do głównego menu"),
    ALL("Pokaż wszystkie wydarzenia"),
    COMING("Pokaż nadchodzące wydarzenia"),
    SEARCH_BY_NAME("Wyszukuj po nazwie"),
    SEARCH_BY_ORGANIZER("Wyszukuj po organizatorze"),
    FILTER_BEFORE("Filtruj wydarzenia do danego momentu"),
    FILTER_AFTER("Filtruj wydarzenia od danego momentu"),
    FILTER_BETWEEN("Filtruj w przedziale czasowym"),
    RESET("Zresetuj listę");

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

