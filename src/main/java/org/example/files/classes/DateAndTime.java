package org.example.files.classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateAndTime {
    public static LocalDate getDate(String zoneIdString) {
        ZoneId zoneId = ZoneId.of(zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        return now.toLocalDate();
    }

    public static LocalTime getTime(String zoneIdString) {
        ZoneId zoneId = ZoneId.of(zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        return now.toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
    }

    public static void main(String[] args) {
        DateAndTime af = new DateAndTime();
        System.out.println(getTime("America/New_York"));
        System.out.println(getDate("America/New_York"));

    }

    public boolean dateExists(String userDate) { // if the entered date exists like lats say january 49
        return false;
    }

    public boolean validityOfDate(String userDate) { // this code checks if the entered date has already passed or is still valid compared to present date
        return false;
    }
}
