package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void getSetStreet() {
        //GIVEN
        Address a = new Address();
        a.setStreet("A");
        String result;

        //WHEN
        result = a.getStreet();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).containsOnlyOnce("A").contains("A").doesNotContainAnyWhitespaces().doesNotContain("X");
    }

    @Test
    void getSetZipcode() {
        //GIVEN
        Address a = new Address();
        a.setZipcode("88-888");
        String result;

        //WHEN
        result = a.getZipcode();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).contains("88-888").containsPattern("^[0-9]{2}-[0-9]{3}$").doesNotContain("X");
    }

    @Test
    void getSetCity() {
        //GIVEN
        Address a = new Address();
        a.setCity("Gdańsk");
        String result;

        //WHEN
        result = a.getCity();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).contains("Gdańsk").doesNotContain("Warszawa");

    }

    @Test
    void getSetLat() {
        //GIVEN
        Address a = new Address();
        a.setLat(10.01);
        Double result;

        //WHEN
        result =a.getLat();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(10.01).isNotEqualTo(555.555);

    }

    @Test
    void getSetLng() {
        //GIVEN
        Address a = new Address();
        a.setLng(10.01);
        Double result;

        //WHEN
        result =a.getLng();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(10.01).isNotEqualTo(555.555);

    }
}