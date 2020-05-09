package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IdCoordinatorTest {

    @Test
    void coordinatingEventResToRealID() {
        //GIVEN
        IdCoordinator idCoordinator = new IdCoordinator();
        List<Event> eventList = new ArrayList<>();
        Map<Integer, Integer> result = new HashMap<>();
        Event e1 = new Event();
        e1.setId(10);
        Event e2 = new Event();
        e2.setId(20);
        Event e3 = new Event();
        e3.setId(30);
        eventList.add(e1);
        eventList.add(e2);
        eventList.add(e3);

        //WHEN
        result = idCoordinator.coordinatingEventResToRealID(eventList);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).hasSize(3).containsKeys(1,2,3).containsValues(10,20,30).doesNotContainKeys(5,6,7,8).doesNotContainValue(55);

    }
}