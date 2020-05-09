package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    void getId() {
        //GIVEN
        Category c = new Category();
        c.setId(99);
        int result;

        //WHEN
        result = c.getId();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(99);

    }

    @Test
    void getName() {
        //GIVEN
        Category c = new Category();
        c.setName("Kino");
        String result;
        //WHEN
        result = c.getName();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("Kino").doesNotContain("Teattr");

    }

    @Test
    void getRootCategory() {
        //GIVEN
        Category c = new Category();
        RootCategory rc = new RootCategory();
        rc.setName("Film");
        rc.setId(99);
        c.setRootCategory(rc);
        RootCategory result;

        //WHEN
        result = c.getRootCategory();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(rc);

    }
}