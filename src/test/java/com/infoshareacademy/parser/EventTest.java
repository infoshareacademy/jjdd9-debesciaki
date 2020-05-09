package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getSetId() {
        //GIVEN
        Event e = new Event();
        e.setId(888);
        int result;

        //WHEN
        result = e.getId();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(888);

    }

    @Test
    void getSetName() {
        //GIVEN
        Event e = new Event();
        e.setName("Name");
        String result;

        //WHEN
        result = e.getName();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Name");

    }

    @Test
    void getSetActive() {
        //GIVEN
        Event e = new Event();
        e.setActive(55);
        int result;

        //WHEN
        result = e.getActive();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(55);

    }

    @Test
    void getSetEndDate() {
        //GIVEN
        Event e = new Event();
        LocalDateTime localDateTime = LocalDateTime.now();
        e.setEndDate(localDateTime);
        LocalDateTime result;

        //WHEN
        result=e.getEndDate();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(localDateTime);

    }

    @Test
    void getSetStartDate() {
        //GIVEN
        Event e = new Event();
        LocalDateTime localDateTime = LocalDateTime.now();
        e.setStartDate(localDateTime);
        LocalDateTime result;

        //WHEN
        result=e.getStartDate();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(localDateTime);

    }
}