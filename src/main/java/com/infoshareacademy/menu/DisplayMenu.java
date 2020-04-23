package com.infoshareacademy.menu;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.Scanner;

public class DisplayMenu <T extends Enum> {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    Scanner scanner = new Scanner(System.in);

    public T showMenu(T[] enums) {
        Integer choice;
        do {
            for (T m : enums) {
                STDOUT.info(m.ordinal() + " - " + m + "\n");
            }
            choice = getIntFromKeyboard();
        } while (choice == null);

        return enums[choice];
    }

    private Integer getIntFromKeyboard() {
      Integer choiceFromKeyboard = scanner.nextInt();
      return choiceFromKeyboard;
    }
}
