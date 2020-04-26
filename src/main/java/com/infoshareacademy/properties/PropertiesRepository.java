package com.infoshareacademy.properties;

import java.util.Properties;

public class PropertiesRepository {
    private static final PropertiesRepository INSTANCE = new PropertiesRepository();
    private Properties properties;

    private PropertiesRepository() {
    }

    public static PropertiesRepository getInstance() {
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
