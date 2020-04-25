package com.infoshareacademy;

public class PropertiesRefresher implements Runnable {
    boolean keepGoing = true;

    public void run() {
        do {
            PropertiesLoader.loadProperties();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
        } while (keepGoing);

    }

    public void stopItNow() {
        this.keepGoing = false;
    }
}
