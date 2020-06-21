package com.infoshareacademy.mail;

public class ReservationDeletedMail implements MailCore {

    public static final String EMAIL_SUBJECT = "Usunięto wydarzenie z rezerwacji: \"%s\"";
    public static final String EMAIL_BODY = "Drogi użytkowniku,%n%n" +
            "Team debeściaki chciałby Cię poinformować o usunięciu rezerwacji na wydarzenie: \"%s\"" +
            "%n%n Przyczyna: \"%s\"" +
            "%n%n Z wyrazami szacunku," +
            "Team Debeściaki";

    private String[] var;


    public ReservationDeletedMail(String... var) {
        this.var = var;
    }

    @Override
    public String buildSubject() {
        return String.format(EMAIL_SUBJECT, var[0]);
    }

    @Override
    public String buildContent() {
        return String.format(EMAIL_BODY, var[0], var[1]);
    }
}

