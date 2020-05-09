package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

class UrlTest {

    @Test
    void getSetTickets() {
        //GIVEN
        Url u = new Url();
        u.setTickets("www.tickets.pl");
        String result;

        //WHEN
        result=u.getTickets();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("www.tickets.pl");

    }

    @Test
    void getSetWww() {
        //GIVEN
        Url u = new Url();
        u.setWww("www.webpage.pl");
        String result;

        //WHEN
        result = u.getWww();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("www.webpage.pl");

    }

    @Test
    void getSetFb() {
        //GIVEN
        Url u = new Url();
        u.setFb("www.fb.pl");
        String result;

        //WHEN
        result = u.getFb();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("www.fb.pl");

    }
}