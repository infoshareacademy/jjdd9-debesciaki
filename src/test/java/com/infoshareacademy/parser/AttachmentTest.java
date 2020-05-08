package com.infoshareacademy.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttachmentTest {

    @Test
    void getSetFileName() {
        //GIVEN
        Attachment a = new Attachment();
        a.setFileName("File");
        String result;

        //WHEN
        result = a.getFileName();

        //THEN
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo("File");

    }
}