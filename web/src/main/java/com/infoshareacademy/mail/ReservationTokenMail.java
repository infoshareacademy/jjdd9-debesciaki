package com.infoshareacademy.mail;

public class ReservationTokenMail implements MailCore {

    public static final String EMAIL_SUBJECT = "Prosimy o potwierdzenie rezerwacji wydarzenia \"%s\"";
    public static final String EMAIL_BODY = "Drogi użytkowniku,%n%n" +
            "Team debeściaki chciałby Cię poinformować o potrzebie potwierdzenia wzięcia udziału w wydarzeniu: \"%s\"." +
            "%n link do potwierdzenia:  \"%s\""+
            "%n%n Z wyrazami szacunku," +
            "Team Debeściaki";

    private String[] var;


    public ReservationTokenMail(String... var) {
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

