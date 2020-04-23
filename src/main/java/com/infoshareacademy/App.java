package com.infoshareacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static void main(String[] args) throws IOException {
        new ParseService().run();
        PropertiesLoad propertiesLoad = new PropertiesLoad();
        new Thread(propertiesLoad.r).start();
        MenuController main = new MenuController();
        main.run();
    }
}
