package com.infoshareacademy.favourites;

import com.infoshareacademy.parser.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddFavouritesTest {

    @Test
    void addFavourite() {
        //GIVEN
        AddFavourites addFavourites = new AddFavourites();
        List<Event> listFavourites = FavouritesRepository.getAllFavouritesList();
        Event e1 = new Event();
        e1.setId(10);

        //WHEN
        addFavourites.addFavourite(e1);

        //THEN
        org.assertj.core.api.Assertions.assertThat(listFavourites).contains(e1);
    }
}