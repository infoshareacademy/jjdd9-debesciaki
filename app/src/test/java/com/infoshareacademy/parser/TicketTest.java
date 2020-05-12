package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

class TicketTest {

    @Test
    void getType() {
        //GIVEN
        Ticket t = new Ticket();
        t.setType("Typ");
        String result;

        //WHEN
        result = t.getType();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Typ");

    }

    @Test
    void getStartTicket() {
        //GIVEN
        Ticket t = new Ticket();
        t.setStartTicket(55);
        int result;

        //WHEN
        result = t.getStartTicket();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(55);

    }

    @Test
    void getSetEndTicket() {
        //GIVEN
        Ticket t = new Ticket();
        t.setEndTicket(55);
        int result;

        //WHEN
        result = t.getEndTicket();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(55);

    }
}