package com.infoshareacademy;

import java.util.Properties;

public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private Properties properties;

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    public void setProperty(String name, String value) {
        this.properties.setProperty(name, value);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
