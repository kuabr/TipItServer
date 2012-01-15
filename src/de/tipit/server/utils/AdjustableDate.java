package de.tipit.server.utils;

import java.util.Date;

import de.tipit.server.transfer.data.LoginParameterTO.SessionDuration;

public class AdjustableDate extends Date {

    private static final long serialVersionUID = 791562839065222087L;

    static final long MILLI_SECONDS_PER_SECOND = 1000L;

    static final long SECONDS_PER_MINUTE = 60L;

    static final long MILLI_SECONDS_PER_MINUTE = MILLI_SECONDS_PER_SECOND * SECONDS_PER_MINUTE;

    static final long MINUTES_PER_HOUR = 60L;

    static final long MILLI_SECONDS_PER_HOUR = MILLI_SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    static final long HOURS_PER_DAY = 24L;

    static final long MILLI_SECONDS_PER_DAY = MILLI_SECONDS_PER_HOUR * HOURS_PER_DAY;

    static final long DAYS_PER_WEEK = 7L;

    static final long MILLI_SECONDS_PER_WEEK = MILLI_SECONDS_PER_DAY * DAYS_PER_WEEK;

    public AdjustableDate() {
        super();
    }

    @SuppressWarnings("deprecation")
    public AdjustableDate(Date other) {
        super(other.getYear(), other.getMonth(), other.getDate(), other.getHours(), other.getMinutes(), other.getSeconds());
    }

    public void add(SessionDuration duration) {
        if (duration.equals(SessionDuration.HOUR)) {
            addHour();
        }

        if (duration.equals(SessionDuration.DAY)) {
            addDay();
        }

        if (duration.equals(SessionDuration.WEEK)) {
            addWeek();
        }

        if (duration.equals(SessionDuration.MONTH)) {
            addMonth();
        }

        if (duration.equals(SessionDuration.YEAR)) {
            addYear();
        }
    }

    public void addHour() {
        super.setTime(super.getTime() + MILLI_SECONDS_PER_HOUR);
    }

    public void addDay() {
        super.setTime(super.getTime() + MILLI_SECONDS_PER_DAY);
    }

    public void addWeek() {
        super.setTime(super.getTime() + MILLI_SECONDS_PER_WEEK);
    }

    @SuppressWarnings("deprecation")
    public void addMonth() {
        int month = super.getMonth() + 1; // months starts with '0'

        if (++month > 12) {
            month = 1;
            addYear();
        }

        super.setMonth(month - 1); // months starts with '0'
    }

    @SuppressWarnings("deprecation")
    public void addYear() {
        super.setYear(super.getYear() + 1);
    }
}
