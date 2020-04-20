package com.infoshareacademy;

public enum MenuSettingsOption {
    RETURN("Powrót"),
    SORT("Sortowanie"),
    DATE_FORMAT("Format daty");

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
