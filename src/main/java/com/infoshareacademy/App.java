package com.infoshareacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static void main(String[] args) {
        MenuController main = new MenuController();
        main.run();
    }
}
