package com.infoshareacademy.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesRefresher implements Runnable {
    private boolean keepGoing = true;
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void run() {
        do {
            PropertiesLoader.loadProperties();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                STDOUT.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        } while (keepGoing);

    }

    public void stopItNow() {
        this.keepGoing = false;
    }
}
