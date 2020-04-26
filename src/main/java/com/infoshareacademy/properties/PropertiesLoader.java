package com.infoshareacademy.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private PropertiesLoader() {
    }

    public static void loadProperties() {
        Properties newProperties = new Properties();
        try (InputStream in = new FileInputStream("config.properties")) {
            newProperties.load(in);
        } catch (IOException e) {
            STDOUT.error("Błąd odczytu pliku.");
        }
        SingletonWithProperties.getInstance().setProperties(newProperties);
    }
}
