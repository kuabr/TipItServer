package de.tipit.server.test;

import java.util.Date;

import de.tipit.server.utils.AdjustableDate;

public class TestAdjustableDate {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        AdjustableDate testDate = new AdjustableDate(new Date());
        testDate.setMonth(11); // = December
        System.out.println("Original: " + testDate);

        testDate.addHour();
        System.out.println("+ Hour  : " + testDate);

        testDate.addDay();
        System.out.println("+ Day   : " + testDate);

        testDate.addWeek();
        System.out.println("+ Week  : " + testDate);

        testDate.addMonth();
        System.out.println("+ Month : " + testDate);

        testDate.addYear();
        System.out.println("+ Year  : " + testDate);
    }
}
