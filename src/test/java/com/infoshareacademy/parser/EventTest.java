package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        result = e.getEndDate();

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
        result = e.getStartDate();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(localDateTime);

    }

    @Test
    void getSetUrls() {
        //GIVEN
        Event e = new Event();
        Url u = new Url();
        u.setFb("FB");
        u.setTickets("TICKETS");
        u.setWww("WWW");
        Url result;
        e.setUrls(u);

        //WHEN
        result = e.getUrls();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(u);
        org.assertj.core.api.Assertions.assertThat(result.getWww()).isEqualTo(u.getWww());
        org.assertj.core.api.Assertions.assertThat(result.getTickets()).isEqualTo(u.getTickets());
        org.assertj.core.api.Assertions.assertThat(result.getFb()).isEqualTo(u.getFb());

    }

    @Test
    void getSetCategoryId() {
        //GIVEN
        Event e = new Event();
        e.setCategoryId(55);
        int result;

        //WHEN
        result = e.getCategoryId();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(55);

    }

    @Test
    void getSetAttachments() {
        //GIVEN
        Event e = new Event();
        List<Attachment> attachments = new ArrayList<>();
        List<Attachment> result = new ArrayList<>();
        Attachment a1 = new Attachment();
        Attachment a2 = new Attachment();
        Attachment a3 = new Attachment();
        a1.setFileName("name1");
        a2.setFileName("name2");
        a3.setFileName("name3");
        attachments.add(a1);
        attachments.add(a2);
        attachments.add(a3);
        e.setAttachments(attachments);

        //WHEN
        result = e.getAttachments();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(attachments);

    }

    @Test
    void getSetDescShort() {
        //GIVEN
        Event e = new Event();
        e.setDescShort("SHORT");
        String result;

        //WHEN
        result = e.getDescShort();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("SHORT");

    }

    @Test
    void getSetDescLong() {
        //GIVEN
        Event e = new Event();
        e.setDescLong("LONG");
        String result;

        //WHEN
        result = e.getDescLong();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("LONG");

    }

    @Test
    void getSetTickets() {
        //GIVEN
        Event e = new Event();
        Ticket t = new Ticket();
        t.setType("typ");
        e.setTickets(t);
        Ticket result;

        //WHEN
        result = e.getTickets();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(t);

    }

    @Test
    void getSetOrganizer() {
        //GIVEN
        Event e = new Event();
        Organizer o = new Organizer();
        o.setDesignation("Things");
        o.setId(11);
        e.setOrganizer(o);
        Organizer result;

        //WHEN
        result = e.getOrganizer();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(o);

    }

    @Test
    void getSetPlace() {
        //GIVEN
        Event e = new Event();
        Place p = new Place();
        Address a = new Address();
        p.setAddress(a);
        e.setPlace(p);
        Place result;

        //WHEN
        result = e.getPlace();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(p);

    }
}