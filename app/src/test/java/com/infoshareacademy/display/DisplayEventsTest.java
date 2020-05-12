package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class DisplayEventsTest {
    @Test
    void filterBefore() {
        //GIVEN
        DisplayEvents displayEvents = new DisplayEvents();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTimeBefore = localDateTimeNow.minusDays(10);
        List<Event> eventList = new ArrayList<>();
        List<Event> result = new ArrayList<>();
        Event e1 = new Event();
        Event e2 = new Event();
        e1.setEndDate(localDateTimeNow.plusDays(15));
        e2.setEndDate(localDateTimeNow.minusDays(15));
        eventList.add(e1);
        eventList.add(e2);

        //WHEN
        result = displayEvents.filterBefore(localDateTimeBefore, eventList);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).containsOnly(e2).doesNotContain(e1);
    }

    @Test
    void filterAfter() {
        //GIVEN
        DisplayEvents displayEvents = new DisplayEvents();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTimeBefore = localDateTimeNow.minusDays(10);
        List<Event> eventList = new ArrayList<>();
        List<Event> result = new ArrayList<>();
        Event e1 = new Event();
        Event e2 = new Event();
        e1.setStartDate(localDateTimeNow.plusDays(15));
        e2.setStartDate(localDateTimeNow.minusDays(15));
        eventList.add(e1);
        eventList.add(e2);

        //WHEN
        result = displayEvents.filterAfter(localDateTimeBefore, eventList);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).containsOnly(e1).doesNotContain(e2);
    }

    @Test
    void filterPeriodically() {
        //GIVEN
        DisplayEvents displayEvents = new DisplayEvents();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTimeStart = localDateTimeNow.minusDays(16);
        LocalDateTime localDateTimeEnd = localDateTimeNow.minusDays(13);
        List<Event> eventList = new ArrayList<>();
        List<Event> result = new ArrayList<>();
        Event e1 = new Event();
        Event e2 = new Event();
        e1.setStartDate(localDateTimeNow.plusDays(15));
        e2.setStartDate(localDateTimeNow.minusDays(15));
        e1.setEndDate(localDateTimeNow.plusDays(16));
        e2.setEndDate(localDateTimeNow.minusDays(14));
        eventList.add(e1);
        eventList.add(e2);

        //WHEN
        result = displayEvents.filterPeriodically(localDateTimeStart, localDateTimeEnd, eventList);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).containsOnly(e2).doesNotContain(e1);
    }

    @Test
    void isAfterNow() {
        //GIVEN
        DisplayEvents displayEvents = new DisplayEvents();
        boolean result;
        boolean result2;
        LocalDateTime localDateTimeAfterNow = LocalDateTime.now().plusDays(1);
        LocalDateTime localDateTimeBeforeNow = LocalDateTime.now().minusDays(1);

        //WHEN
        result = displayEvents.isAfterNow(localDateTimeAfterNow);
        result2 = displayEvents.isAfterNow(localDateTimeBeforeNow);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isTrue();
        org.assertj.core.api.Assertions.assertThat(result2).isFalse();

    }
}