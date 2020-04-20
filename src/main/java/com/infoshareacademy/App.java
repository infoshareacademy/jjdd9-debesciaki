package com.infoshareacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
public class App {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static void main(String[] args) {

        STDOUT.info("Hello World!");
    }
}
