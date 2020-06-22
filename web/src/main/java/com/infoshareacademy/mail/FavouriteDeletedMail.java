package com.infoshareacademy.mail;

public class FavouriteDeletedMail implements MailCore {

    public static final String EMAIL_SUBJECT = "Usunięto wydarzenie z ulubionych: \"%s\"";
    public static final String EMAIL_BODY = "Drogi użytkowniku,%n%n" +
            "Team debeściaki chciałby Cię poinformować o usunięciu z ulubionych wydarzenia: \"%s\"" +
            "%n%n Przyczyna: \"%s\"" +
            "%n%n Z wyrazami szacunku," +
            "Team Debeściaki";

    private String[] var;


    public FavouriteDeletedMail(String... var) {
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

