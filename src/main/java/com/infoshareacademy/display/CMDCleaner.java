package com.infoshareacademy.display;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CMDCleaner {
    private static final String os = System.getProperty("os.name").toLowerCase();
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public static void cleanConsole() {
        try {
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                STDOUT.info("\033\143");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

