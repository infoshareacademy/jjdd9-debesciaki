package com.infoshareacademy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static void loadProperties() {
        Properties newProperties = new Properties();
        try (InputStream in = new FileInputStream("config.properties")) {
            newProperties.load(in);
        } catch (IOException e) {
        }
        SingletonWithProperties.getInstance().setProperties(newProperties);
    }
}
