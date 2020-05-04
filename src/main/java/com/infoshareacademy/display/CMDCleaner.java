package com.infoshareacademy.display;

import com.infoshareacademy.properties.PropertiesRepository;
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
                STDOUT.info("{}WYDARZENIA KULTURALNE W GDAŃSKU{}\n",ConsoleColor.GREEN_BOLD_BRIGHT,ConsoleColor.RESET);
            } else {
                STDOUT.info("\033\143");
                STDOUT.info("{}WYDARZENIA KULTURALNE W GDAŃSKU{}\n",ConsoleColor.GREEN_BOLD_BRIGHT,ConsoleColor.RESET);
                STDOUT.info("{}>>>{}{}",ConsoleColor.BLACK_BACKGROUND_BRIGHT,PropertiesRepository.getInstance().getBreadcrumb(),ConsoleColor.RESET);
                STDOUT.info("\n");
            }
        } catch (IOException | InterruptedException e) {
            STDOUT.info("{}\n", e.getMessage());
        }
    }
}