package com.infoshareacademy.display;// CMDCleaner.cleanConsole(); - wywolanie
import java.io.IOException;

public class CMDCleaner {
    private static final String os = System.getProperty("os.name").toLowerCase();

    public static void cleanConsole() {
        try {
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

