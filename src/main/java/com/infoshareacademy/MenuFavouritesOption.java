package com.infoshareacademy;

public enum MenuFavouritesOption {
    RETURN("Powrót"),
    ADD("Dodaj"),
    DELETE("Usuń"),
    SHOW_EARLIEST("Pokaż najbliższe wydarzenia");

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
