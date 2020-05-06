package com.infoshareacademy.favourites;

import com.infoshareacademy.properties.PropertiesRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class RemoveFavourites {

    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private ShowFavourites showFavourites = new ShowFavourites();

    public void run(boolean tooMany) {
        showFavourites.run();
        Integer choiceFromKeyboard = null;
        Scanner scanner = new Scanner(System.in);
        if (tooMany) {
            STDOUT.info("Maksymalna liczba ulubionych wydarzeń - 3\n");
            STDOUT.info("Wybierz wydarzenie, które chciałbyś usunąć (wpisz 0 aby cofnąć): ");
        } else {
            STDOUT.info("Wybierz numer wydarzenia, które chciałbyś usunąć (wpisz 0 aby cofnąć): ");
        }

        while (choiceFromKeyboard == null) {
            String in = scanner.nextLine();
            if (!NumberUtils.isDigits(in)) {
                choiceFromKeyboard = null;
            } else {
                choiceFromKeyboard = Integer.parseInt(in);
                for (int i = 0; i < FavouritesRepository.getAllFavouritesList().size(); i++) {
                    if (choiceFromKeyboard != FavouritesRepository.getAllFavouritesList().get(i).getId() && choiceFromKeyboard != 0) {
                        if (i == FavouritesRepository.getAllFavouritesList().size() - 1) {
                            choiceFromKeyboard = null;
                        }
                    } else {
                        break;
                    }
                }
            }

            if (choiceFromKeyboard == null) {
                STDOUT.info("Nie ma takiego numeru wydarzenia wśród ulubionych - wybierz ponownie: ");
            }
        }

        if (choiceFromKeyboard != 0) {
            int index = 0;
            for (int i = 0; i < FavouritesRepository.getAllFavouritesList().size(); i++) {
                if (FavouritesRepository.getAllFavouritesList().get(i).getId() == choiceFromKeyboard) {
                    index = i;
                }
            }

            FavouritesRepository.getAllFavouritesList().remove(index);
            STDOUT.info("Wydarzenie numer {} zostało usunięte z ulubionych.\n", choiceFromKeyboard);
            PropertiesRepository.getInstance().removeBreadcrumb();
        } else {
            PropertiesRepository.getInstance().removeBreadcrumb();
        }
    }
}
