package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RootCategoryTest {

    @Test
    void getSetId() {
        //GIVEN
        RootCategory rc = new RootCategory();
        rc.setId(99);
        int result;

        //WHEN
        result =rc.getId();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(99);

    }

    @Test
    void getSetName() {
        //GIVEN
        RootCategory rc = new RootCategory();
        rc.setName("Film");
        String result;

        //WHEN
        result = rc.getName();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Film");

    }
}