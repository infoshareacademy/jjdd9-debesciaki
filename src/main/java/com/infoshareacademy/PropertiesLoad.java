package com.infoshareacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesLoad extends Thread {
    private Properties properties = new Properties();
    private FileInputStream in;
    private List<Properties> listOfProperties = new ArrayList<>();
    private static final int DELAY = 3000;
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public PropertiesLoad() throws IOException {
        this.in = new FileInputStream("config.properties");
        this.properties.load(in);
        listOfProperties.add(this.properties);
        in.close();
    }

    public void loadProperties() {
        PropertyRepository.setAllProperties(listOfProperties);
        System.out.println(listOfProperties);
    }

    public Runnable r = new Runnable() {
        @Override
        public void run() {
            do {
                try {
                    new PropertiesLoad();
                    loadProperties();
                    Thread.sleep(DELAY);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    };

}

