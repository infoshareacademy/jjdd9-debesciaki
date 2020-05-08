package com.infoshareacademy.display;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class EventPrinterTest {
    @Test
    void dateConfiguratorDigit() {
        //GIVEN
        EventPrinter eventPrinter = new EventPrinter();
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 12, 12, 12);
        String pattern = "yyyy/MM/dd HH:mm";
        String expected = "2020/12/12 12:12";

        //WHEN
        String result = eventPrinter.dateConfigurator(localDateTime, pattern);

        //THEN
        Assertions.assertThat(result).isEqualTo(expected);
    }
    @Test
    void dateConfiguratorShort() {
        //GIVEN
        EventPrinter eventPrinter = new EventPrinter();
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 12, 12, 12);
        String pattern = "yyyy/MMM/dd HH:mm";
        String expected = "2020/gru/12 12:12";

        //WHEN
        String result = eventPrinter.dateConfigurator(localDateTime, pattern);

        //THEN
        Assertions.assertThat(result).isEqualTo(expected);
    }
    @Test
    void dateConfiguratorLong() {
        //GIVEN
        EventPrinter eventPrinter = new EventPrinter();
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 12, 12, 12);
        String pattern = "yyyy/MMMM/dd HH:mm";
        String expected = "2020/grudnia/12 12:12";

        //WHEN
        String result = eventPrinter.dateConfigurator(localDateTime, pattern);

        //THEN
        Assertions.assertThat(result).isEqualTo(expected);
    }

}