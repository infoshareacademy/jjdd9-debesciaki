package com.infoshareacademy.menu;

import com.infoshareacademy.display.CMDCleaner;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Scanner;

public class DisplayMenu<T extends Enum> {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    Scanner scanner = new Scanner(System.in);

    public T showMenu(T[] enums) {
        Integer choice;

        do {
            for(int i = 1; i < enums.length; i++) {
                STDOUT.info(i + " - " + enums[i] + "\n");
            }
            STDOUT.info(0 + " - " + enums[0] + "\n");
            STDOUT.info("Twój wybór to: ");
            choice = getIntFromKeyboard();
            if (choice == null || choice >= enums.length) {
                CMDCleaner.cleanConsole();
                STDOUT.info("Błędne dane, wybierz ponownie.\nWybierz ponownie:\n ");
                choice = null;
            }
        } while (choice == null);

        return enums[choice];
    }

    private Integer getIntFromKeyboard() {
        String in = scanner.nextLine();
        while (!NumberUtils.isDigits(in)) {
            return null;
        }
        return Integer.parseInt(in);
    }
}
