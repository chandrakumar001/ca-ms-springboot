package com.chstore.ca.ms.tracking;

import java.util.Locale;


public enum CHRequestLanguage {

    DE(Locale.GERMAN),
    FR(Locale.FRENCH),
    IT(Locale.ITALIAN),
    EN(Locale.ENGLISH);

    private final Locale locale;

    CHRequestLanguage(final Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}