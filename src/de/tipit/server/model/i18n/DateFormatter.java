package de.tipit.server.model.i18n;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    private static final SimpleDateFormat DATE_FORMAT_DE = new SimpleDateFormat("dd.MM.yy");

    private static final SimpleDateFormat TIME_FORMAT_DE = new SimpleDateFormat("HH:mm:ss");

    private static final SimpleDateFormat DATE_FORMAT_EN = new SimpleDateFormat("MM/dd/yy");

    private static final SimpleDateFormat TIME_FORMAT_EN = new SimpleDateFormat("HH.mm�ss");

    public static String getDateDE(Date dateTime) {
        return DATE_FORMAT_DE.format(dateTime);
    }

    public static String getTimeDE(Date dateTime) {
        return TIME_FORMAT_DE.format(dateTime);
    }

    public static String getDateEN(Date dateTime) {
        return DATE_FORMAT_EN.format(dateTime);
    }

    public static String getTimeEN(Date dateTime) {
        return TIME_FORMAT_EN.format(dateTime);
    }
}
