package com.infoshareacademy;

import java.util.Properties;

public class SingletonWithProperties {
    private static final SingletonWithProperties INSTANCE = new SingletonWithProperties();
    private Properties properties;

    private SingletonWithProperties() {
    }

    public static SingletonWithProperties getInstance() {
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
