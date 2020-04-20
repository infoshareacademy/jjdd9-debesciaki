package com.infoshareacademy;

import java.util.Scanner;

public class DisplayMenu <T extends Enum> {
    Scanner scanner = new Scanner(System.in);

    public T showMenu(T[] enums) {
        Integer choice;
        do {
            for (T m : enums) {
                System.out.println(m.ordinal() + " - " + m);
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
