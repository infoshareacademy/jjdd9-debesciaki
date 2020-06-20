package com.infoshareacademy.mail;

public class EventFinishedMail implements MailCore {

    public static final String EMAIL_SUBJECT = "Koniec wydarzenia \"%s\"";
    public static final String EMAIL_BODY = "Drogi użytkowniku,%n%n" +
            "Team debeściaki chciałby Cię poinformować o usunięciu wydarzenia z Twojej listy ulubionych z uwagi na jego zakończenie: \"%s\"" +
            "%n%n Z wyrazami szacunku," +
            "Team Debeściaki";

    private String[] var;


    public EventFinishedMail(String... var) {
        this.var = var;
    }

    @Override
    public String buildSubject() {
        return String.format(EMAIL_SUBJECT, var[0]);
    }

    @Override
    public String buildContent() {
        return String.format(EMAIL_BODY, var[0]);
    }
}

