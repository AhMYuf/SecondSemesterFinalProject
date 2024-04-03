package org.example.files.classes;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * This class deals with everything relative to time & date.
 */
public class DateAndTime {

    String zoneIdString;

    /**
     * This method gets the current date of an individual depending on their time zone
     *
     * @param zoneIdString inputted time zone
     * @return String date
     */
    public static LocalDate getDate(String zoneIdString) {
        ZoneId zoneId = ZoneId.of(zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        return now.toLocalDate();
    }

    /**
     * This method gets the time of an individual depending on their time zone
     *
     * @param zoneIdString inputted time zone
     * @return String time
     */
    public static LocalTime getTime(String zoneIdString) {
        ZoneId zoneId = ZoneId.of(zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        return now.toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
    }

    /**
     * This method checks whether a date actually exists or not
     *
     * @param dateString    inputted date
     * @param formatPattern the inputted format pattern
     * @return boolean
     */
    public boolean dateExists(String dateString, String formatPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
        sdf.setLenient(false);

        try {
            sdf.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * This method checks whether a date is still valid today (has yet to come)
     *
     * @param dateString inputted date
     * @return boolean
     */
    public boolean dateValidToday(String dateString) {
        return getDate(getZoneIdString()).equals(dateString);
    }

    public String getZoneIdString() {
        return zoneIdString;
    }

    public void setZoneIdString(String zoneIdString) {
        this.zoneIdString = zoneIdString;
    }
}
