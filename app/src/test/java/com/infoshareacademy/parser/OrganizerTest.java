package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

class OrganizerTest {

    @Test
    void getSetId() {
        //GIVEN
        Organizer o = new Organizer();
        o.setId(11);
        int result;

        //WHEN
        result = o.getId();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(11);

    }

    @Test
    void getSetDesignation() {
        //GIVEN
        Organizer o = new Organizer();
        o.setDesignation("Things");
        String result;

        //WHEN
        result = o.getDesignation();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Things");

    }

    @Test
    void toStringTest() {
        //GIVEN
        Organizer o = new Organizer();
        o.setDesignation("Things");
        o.setId(11);
        String result;

        //WHEN
        result = o.toString();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("id of entity: 11 and the name of entity: Things");

    }
}