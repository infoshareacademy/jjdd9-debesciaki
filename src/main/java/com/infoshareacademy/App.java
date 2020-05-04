package com.infoshareacademy;

import com.infoshareacademy.menu.MenuController;
import com.infoshareacademy.parser.ParseService;
import com.infoshareacademy.properties.PropertiesRefresher;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        new ParseService().parseFiles();

        PropertiesRefresher propertiesRefresher = new PropertiesRefresher();
        Thread thread = new Thread(propertiesRefresher);
        thread.start();

        MenuController main = new MenuController();
        main.run();
        propertiesRefresher.stopItNow();
    }
}
