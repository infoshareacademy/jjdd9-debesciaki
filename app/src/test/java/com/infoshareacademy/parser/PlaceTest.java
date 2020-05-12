package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {

    @Test
    void getSetId() {
        //GIVEN
        Place p =new Place();
        p.setId(55);
        int result;
        //WHEN
       result= p.getId();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(55);

    }

    @Test
    void getSetSubname() {
        //GIVEN
        Place p =new Place();
        p.setSubname("Subname");
        String result;

        //WHEN
        result = p.getSubname();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Subname");

    }

    @Test
    void getSetName() {
        //GIVEN
        Place p =new Place();
        p.setName("Name");
        String result;

        //WHEN
        result = p.getName();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Name");

    }

    @Test
    void getAddress() {
        //GIVEN
        Place p =new Place();
        Address a = new Address();
        p.setAddress(a);
        Address result;

        //WHEN
        result = p.getAddress();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(a);

    }
}