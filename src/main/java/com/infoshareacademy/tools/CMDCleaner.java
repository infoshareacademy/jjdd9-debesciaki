package com.infoshareacademy.tools;

import java.io.IOException;

public class CMDCleaner {
    private static final String os = System.getProperty("os.name").toLowerCase();

    public CMDCleaner() {
    }

    public static void cleanConsole() {
        try {
            if (os.contains("win")) {
                (new ProcessBuilder(new String[]{"cmd", "/c", "cls"})).inheritIO().start().waitFor();
            } else {
                System.out.print("\u001b[H\u001b[2J");
                System.out.flush();
            }
        } catch (InterruptedException | IOException var1) {
            var1.printStackTrace();
        }

    }
}


