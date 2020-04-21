package com.infoshareacademy;

public enum MenuSettingsOption {
    RETURN("Powrót do menu główego"),
    SORT("Ustawienia sortowania"),
    DATE_FORMAT("Ustawienia formatu daty");

    private String description;

    MenuSettingsOption(String description) {
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
