package com.infoshareacademy.menu;

import com.infoshareacademy.display.CMDCleaner;
import com.infoshareacademy.display.ConsoleColor;
import com.infoshareacademy.favourites.ShowUpcoming;
import com.infoshareacademy.properties.PropertiesRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class DisplayMenu<T extends Enum> {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    Scanner scanner = new Scanner(System.in);
    Integer choice = null;

    public T showMenu(T[] enums) throws IOException {
        do {
            if (PropertiesRepository.getInstance().getProperty("homeOnly").equals("false")) {
                new ShowUpcoming();
            }
            for (int i = 1; i < enums.length; i++) {
                STDOUT.info(i + " - " + enums[i] + "\n");
            }
            STDOUT.info(0 + " - " + enums[0] + "\n");
            STDOUT.info("Twój wybór to: ");
            choice = tryGetChoiceFromKeyboard(enums.length);
        } while (choice == null);

        return enums[choice];
    }

    public Integer tryGetChoiceFromKeyboard(int length) {
        String in = scanner.nextLine();

        if (!NumberUtils.isDigits(in)) {
            choice = null;
        } else {
            choice = Integer.parseInt(in);
        }

        if (choice == null || choice >= length) {
            CMDCleaner.cleanConsole();
            STDOUT.info("{}Błędne dane! Wybierz ponownie:\n{}", ConsoleColor.RED,ConsoleColor.RESET);
            choice = null;
        }
        return choice;
    }
}
