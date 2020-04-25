
package com.infoshareacademy.menu;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DisplayMenu<T extends Enum> {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    Scanner scanner = new Scanner(System.in);

    public T showMenu(T[] enums) {
        Integer choice = null;
        System.out.println(enums.length);
        do {
            for (T m : enums) {
                STDOUT.info(m.ordinal() + " - " + m + "\n");
            }
            choice = getIntFromKeyboard();
            if (choice >= enums.length) {
                STDOUT.info("Podaj poprawną wartość\n");
                choice = null;
            }
        } while (choice == null);

        return enums[choice];
    }

    private Integer getIntFromKeyboard() {
        String choiceFromKeyboard = scanner.nextLine();
        Integer i;
        try {
             return i = Integer.parseInt(choiceFromKeyboard);
        } catch (NumberFormatException e) {
            STDOUT.info("Błędne dane. Wprowadź ponownie!\n");
            i = getIntFromKeyboard();
        }
        return i;
    }
}