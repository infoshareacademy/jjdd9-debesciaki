package com.infoshareacademy.properties;

import java.util.Properties;
import java.util.Stack;

public class PropertiesRepository {
    private static final PropertiesRepository INSTANCE = new PropertiesRepository();
    private Properties properties;
    private Stack<String> breadcrumb = new Stack<>();

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

    public void putBreadcrumb(String actualDisplay) {
        breadcrumb.push(actualDisplay);
    }

    public void removeBreadcrumb() {
        breadcrumb.pop();
    }

    public String getBreadcrumb() {
        return breadcrumb.stream()
                .reduce("", (acc, e) -> acc.equals("") ? e : acc + ">>>" + e);
    }

}
