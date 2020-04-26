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
            for (T m : enums) {
                STDOUT.info(m.ordinal() + " - " + m + "\n");
            }
            choice = getIntFromKeyboard();
            if (choice >= enums.length) {
                CMDCleaner.cleanConsole();
                STDOUT.info("Wybierz ponownie: \n");
                choice = null;
            }
        } while (choice == null);

        return enums[choice];
    }

    private Integer getIntFromKeyboard() {
        String in = scanner.nextLine();
        while (!NumberUtils.isDigits(in)) {
            STDOUT.info("Błędne dane, wybierz ponownie.\n");
            in = scanner.nextLine();
        }
        return Integer.parseInt(in);
    }
}
