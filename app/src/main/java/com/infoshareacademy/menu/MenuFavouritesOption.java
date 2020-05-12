package com.infoshareacademy.menu;

public enum MenuFavouritesOption {
    RETURN("Powrót do menu głównego"),
    ADD("Dodaj ulubione wydarzenie"),
    DELETE("Usuń z ulubionych wydarzeń");

    private String description;

    MenuFavouritesOption(String description) {
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
