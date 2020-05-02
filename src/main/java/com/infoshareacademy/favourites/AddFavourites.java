package com.infoshareacademy.favourites;

import com.infoshareacademy.parser.Event;

import java.io.IOException;
import java.util.List;


public class AddFavourites {
    ShowFavourites showFavourites = new ShowFavourites();
    List<Event> listFavourites = showFavourites.getEvents();
    RemoveFavourites removeFavourite = new RemoveFavourites();

    public AddFavourites() throws IOException {
    }

    public void run() throws IOException {
        if (listFavourites.size() >= 3) {
            removeFavourite.run();
        }
        Event event = new Event();
        addFavourite(event);
    }

    private void addFavourite(Event event) {
        listFavourites.add(event);
    }
}
