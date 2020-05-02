package com.infoshareacademy.favourites;

import com.infoshareacademy.display.Display;
import com.infoshareacademy.parser.Event;
import com.infoshareacademy.properties.PropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ShowUpcoming {
    private final static Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    ShowFavourites showFavourites = new ShowFavourites();
    List<Event> listFavourites = showFavourites.getEvents();
    Display display = new Display();

    public ShowUpcoming() throws IOException {
        run();
    }

    public void run() {
        if (listFavourites.isEmpty()) {
            STDOUT.info("BRAK INFOMRACJI O NAJBLIŻSZYM ULUBIONYM WYDARZENIU");
        } else {
            Event upcomingEvent = listFavourites.get(0);
            for (int i = 0; i < listFavourites.size(); i++) {
                if (listFavourites.get(i).getStartDate().isBefore(upcomingEvent.getStartDate())) {
                    upcomingEvent = listFavourites.get(i);
                }
            }
            display.consolePrintEventScheme(upcomingEvent, PropertiesRepository.getInstance().getProperty("date-format"));
        }
    }
}
