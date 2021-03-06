package com.infoshareacademy.favourites;

import com.infoshareacademy.parser.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;


public class AddFavourites {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private List<Event> listFavourites = FavouritesRepository.getAllFavouritesList();
    private RemoveFavourites removeFavourite = new RemoveFavourites();
    private ShowComingForAdd displayEvents = new ShowComingForAdd();

    public void run() {
        if (listFavourites.size() >= 3) {
            removeFavourite.run(true);
            return;
        }
        STDOUT.info("Wybierz wydarzenia, które chciałbyś dodać do ulubionych.\n");
        displayEvents.displayComingEvents();
    }

    public void addFavourite(Event event) {
        if (FavouritesRepository.getAllFavouritesList().size() >= 3) {
            removeFavourite.run(true);
        }

        for (Event el : listFavourites) {
            if (event.equals(el)) {
                STDOUT.info("To wydarzenie należy już do ulubionych.\n");
                return;
            }
        }
        listFavourites.add(event);
        listFavourites.stream().distinct().collect(Collectors.toList());
        FavouritesRepository.setAllFavouritesList(listFavourites);
        new SaveToFileWithFavourites().run(listFavourites);

    }
}
