package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;
import com.infoshareacademy.parser.Organizer;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class EventComparatorsTest {
    @Test
    void byNameAsc_expect1() {
        //GIVEN
        Event e1 = new Event();
        e1.setName("Name");
        Event e2 = new Event();
        e2.setName("Mame");
        int result;

        //WHEN
        result = EventComparators.EventNameComparatorAsc.compare(e1, e2);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(1);

    }

    @Test
    void byNameAsc_expect0() {
        //GIVEN
        Event e1 = new Event();
        e1.setName("Name");
        Event e2 = new Event();
        e2.setName("Name");
        int result;

        //WHEN
        result = EventComparators.EventNameComparatorAsc.compare(e1, e2);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(0);

    }

    @Test
    void byNameAsc_expect_1() {
        //GIVEN
        Event e1 = new Event();
        e1.setName("Name");
        Event e2 = new Event();
        e2.setName("Mame");
        int result;

        //WHEN
        result = EventComparators.EventNameComparatorAsc.compare(e2, e1);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(-1);

    }

    @Test
    void byNameDesc_expect1() {
        //GIVEN
        Event e1 = new Event();
        e1.setName("Name");
        Event e2 = new Event();
        e2.setName("Mame");
        int result;

        //WHEN
        result = EventComparators.EventNameComparatorDesc.compare(e2, e1);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(1);

    }

    @Test
    void byDateAsc_expect1() {
        //GIVEN
        Event e1 = new Event();
        Event e2 = new Event();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTimeBefore = localDateTimeNow.minusDays(1);
        e1.setEndDate(localDateTimeNow);
        e2.setEndDate(localDateTimeBefore);
        int result;

        //WHEN
        result = EventComparators.EventEndDateComparatorAsc.compare(e1, e2);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(1);

    }

    @Test
    void byDateDesc_expect1() {
        //GIVEN
        Event e1 = new Event();
        Event e2 = new Event();
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTimeBefore = localDateTimeNow.minusDays(1);
        e1.setEndDate(localDateTimeNow);
        e2.setEndDate(localDateTimeBefore);
        int result;

        //WHEN
        result = EventComparators.EventEndDateComparatorDesc.compare(e2, e1);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(1);

    }

    @Test
    void byOrganizerAsc_expect1() {
        //GIVEN
        Event e1 = new Event();
        Event e2 = new Event();
        Organizer o1 = new Organizer();
        Organizer o2 = new Organizer();
        o1.setDesignation("A");
        o2.setDesignation("B");
        e1.setOrganizer(o1);
        e2.setOrganizer(o2);
        int result;

        //WHEN
        result = EventComparators.EventOrganizerComparatorAsc.compare(e2,e1);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(1);

    }

    @Test
    void byOrganizerDesc_expect1() {
        //GIVEN
        Event e1 = new Event();
        Event e2 = new Event();
        Organizer o1 = new Organizer();
        Organizer o2 = new Organizer();
        o1.setDesignation("A");
        o2.setDesignation("B");
        e1.setOrganizer(o1);
        e2.setOrganizer(o2);
        int result;

        //WHEN
        result = EventComparators.EventOrganizerComparatorDesc.compare(e1,e2);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(1);

    }
    @Test
    void byOrganizerDesc_expect0() {
        //GIVEN
        Event e1 = new Event();
        Event e2 = new Event();
        Organizer o1 = new Organizer();
        Organizer o2 = new Organizer();
        o1.setDesignation("A");
        o2.setDesignation("A");
        e1.setOrganizer(o1);
        e2.setOrganizer(o2);
        int result;

        //WHEN
        result = EventComparators.EventOrganizerComparatorDesc.compare(e1,e2);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(0);

    }

    @Test
    void byOrganizerDesc_expect_1() {
        //GIVEN
        Event e1 = new Event();
        Event e2 = new Event();
        Organizer o1 = new Organizer();
        Organizer o2 = new Organizer();
        o1.setDesignation("A");
        o2.setDesignation("B");
        e1.setOrganizer(o1);
        e2.setOrganizer(o2);
        int result;

        //WHEN
        result = EventComparators.EventOrganizerComparatorDesc.compare(e2,e1);

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(-1);

    }
}