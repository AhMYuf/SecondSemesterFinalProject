package org.example.files.classes;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;

/**
 * This class deals with everything relative to time & date.
 */
public class DateAndTime implements Comparator<DateAndTime> {

    String zoneIdString = "America/New_York";

    public static void main(String[] args) {
        DateAndTime dateAndTime = new DateAndTime();
        System.out.println(dateAndTime.getTime());
    }

    /**
     * This method gets the current date of an individual depending on their time zone
     *
     * @return String date
     */
    public LocalDate getDate() {
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        return now.toLocalDate();
    }

    /**
     * This method gets the time of an individual depending on their time zone
     *
     * @return String time
     */
    public LocalTime getTime() {
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        return now.toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
    }

    /**
     * This method checks whether a date actually exists or not
     *
     * @param dateString    inputted date
     * @param pattern the inputted format pattern
     * @return boolean
     */
    public boolean dateExists(String dateString, String pattern) {
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid date pattern: " + pattern);
            return false;
        }
        try {
            sdf.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public String getZoneIdString() {
        return zoneIdString;
    }

    public void setZoneIdString(String zoneIdString) {
        this.zoneIdString = zoneIdString;
    }

    /**
     * This method checks whether a date is still valid today (has yet to come)
     *
     * @param dateString inputted date
     * @return boolean
     */
    public boolean dateValidToday(String dateString) {
        return getDate().equals(dateString);
    }

    /*
    compares the todays date format and the entered date format of the entered date
    then I jhave to create a method that will essentially change it to maake it the same
     */
    @Override
    public int compare(DateAndTime o1, DateAndTime o2) {
        //o1.getDate()



        return 0;
    }

}
