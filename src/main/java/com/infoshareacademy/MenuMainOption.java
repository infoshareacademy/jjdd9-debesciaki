package com.infoshareacademy;

public enum MenuMainOption {
    EXIT("Zakończ program"),
    SHOW_EVENTS("Pokaż wydarzenia"),
    SHOW_FAVOURITES("Pokaż ulubione"),
    SETTINGS("Ustawienia");

    private String description;

    MenuMainOption(String description) {
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
