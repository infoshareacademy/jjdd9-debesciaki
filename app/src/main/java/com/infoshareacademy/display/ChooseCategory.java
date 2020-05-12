package com.infoshareacademy.display;

import com.infoshareacademy.parser.Category;
import com.infoshareacademy.repository.CategoryRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static com.infoshareacademy.display.CMDCleaner.cleanConsole;

public class ChooseCategory {
    Map<Integer, Category> categoryMap;
    List<Category> categoryList;
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");


    public ChooseCategory() {
        this.categoryMap = CategoryRepository.getAllCategoriesMap();
        this.categoryList = CategoryRepository.getAllCategories();

    }

    public int displayGenres() {
        Optional<Integer> decision;
        int maxId = 10;
        do {
            cleanConsole();
            for (Category c : categoryList) {
                if (c.getId() <= 10) {
                    STDOUT.info("{} - {}\n", maxId = c.getId(), c.getName());
                }
            }
            STDOUT.info("0 - Wyjście\n");
            decision = inputInteger("Wybierz interesującą Cię kategorię: ");
        } while (decision.get() < 0 || decision.get() > maxId);
        if (decision.get() != 0) return displaySubgenre(decision.get());
        return 0;
    }

    private int displaySubgenre(int d) {
        Optional<Integer> decision;
        int maxId = d * 100 + 99;
        do {
            cleanConsole();
            for (Category c : categoryList) {
                int id = c.getId();
                if (id >= (d * 100) && id < ((d + 1) * 100)) {
                    STDOUT.info("{} - {}\n", maxId = c.getId(), c.getName());
                }
            }
            STDOUT.info("0 - Wyjście\n");
            decision = inputInteger("Wybierz interesującą Cię kategorię: ");
        } while ((decision.get() < d * 100 || decision.get() > maxId) && decision.get() != 0);
        return decision.get();
    }

    private Optional<Integer> inputInteger(String subject) {
        Integer quantity = null;
        Optional<Integer> opt = null;
        String in;
        do {
            STDOUT.info("{}", subject);
            Scanner scanner = new Scanner(System.in);
            in = scanner.nextLine();
            if (NumberUtils.isDigits(in)) {
                quantity = Integer.parseInt(in);
            }
            opt = Optional.ofNullable(quantity);
            if (!NumberUtils.isDigits(in)) {
                cleanConsole();
                STDOUT.info("Źle wprowadzone dane, spróbuj ponownie!\n");
            }
        } while (opt.isEmpty());
        return opt;
    }
}
