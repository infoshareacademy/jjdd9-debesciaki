package com.infoshareacademy;

import com.infoshareacademy.menu.MenuController;
import com.infoshareacademy.parser.ParseService;
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

        PropertiesRefresher propertiesRefresher = new PropertiesRefresher();
        Thread thread = new Thread(propertiesRefresher);
        thread.start();

        MenuController main = new MenuController();
        main.run();
        propertiesRefresher.stopItNow();
    }
}
