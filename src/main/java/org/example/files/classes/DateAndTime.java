package org.example.files.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * This class handles operations related to date and time.
 */
public class DateAndTime {
    /**
     * The time zone ID string.
     */
    private String zoneIdString = "America/New_York";

    /**
     * The pattern for day formatting.
     */
    private String patternDay = "dd-MM-yyyy";

    /**
     * The pattern for hour formatting.
     */
    private String patternHour = "HH:mm:ss";

    /**
     * Constructs a DateAndTime object with default settings.
     */
    public DateAndTime() {
    }


    /**
     * Constructs a DateAndTime object with the specified settings.
     *
     * @param zoneIdString The time zone ID string.
     * @param patternDay   The pattern for day formatting.
     * @param patternHour  The pattern for hour formatting.
     */
    public DateAndTime(String zoneIdString, String patternDay, String patternHour) {
        this.zoneIdString = zoneIdString;
        this.patternDay = patternDay;
        this.patternHour = patternHour;
    }

    /**
     * Gets the current date based on the time zone.
     *
     * @return The current date.
     */
    public String getDate(String zoneIdString) {
        ZoneId zoneId = ZoneId.of(zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternDay);
        return now.format(formatter);
    }

    /**
     * Sets the time range and validates it.
     *
     * @param start The start time.
     * @param end   The end time.
     */
    void setTime(String start, String end) {
        try {
            LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HH:mm:ss"));

            if (startTime.isBefore(endTime)) {
                String timeRange = start + " - " + end;
                System.out.println("Valid time range: " + timeRange);
            } else {
                System.out.println("Invalid time range: Start time must be before end time.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please use HH:mm:ss format.");
        }
    }

    /**
     * Gets the current time based on the time zone.
     *
     * @param patternHour The pattern for hour formatting.
     * @return The current time.
     */
    public String getTime(String patternHour) {
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternHour);
        return now.format(formatter);
    }

    /**
     * Checks if a given date exists.
     *
     * @param dateString The date string to check.
     * @return True if the date exists, false otherwise.
     */
    public boolean dateExists(String dateString) {
        SimpleDateFormat sdf;
        try {
            sdf = new SimpleDateFormat(patternDay);
            sdf.setLenient(false);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid date pattern: ");
            return false;
        }
        try {
            sdf.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Sets the time zone string.
     *
     * @param Continent The continent name.
     * @param City      The city name.
     * @param option    The option to set (1 or 2).
     * @throws IllegalArgumentException If the option is invalid.
     */
    public void setZoneIdString(String Continent, String City, int option) throws IllegalArgumentException {
        int slash = zoneIdString.indexOf('/');
        if (option == 1)
            zoneIdString = Continent + "/" + City;
        else
            zoneIdString = zoneIdString.substring(0, slash) + "/" + City;
    }

    /**
     * Sets the time zone string.
     *
     * @param zoneId The time zone ID string.
     */
    public void setZoneIdString(String zoneId) {
        this.zoneIdString = zoneId;
    }

    /**
     * Checks if a date is valid for today.
     *
     * @param dateString The date string to check.
     * @return True if the date is valid for today, false otherwise.
     */
    public boolean dateValidToday(String dateString) {
        return getDate(getZoneIdString()).equals(dateString);
    }

    /**
     * Compares a given date and time to the current date and time and returns the difference.
     *
     * @param dateString The date string to compare.
     * @param timeString The time string to compare.
     */
    public String compareDateTime(String dateString, String timeString) {
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);

        LocalDate date;
        LocalTime time;
        try {
            date = LocalDate.parse(dateString, java.time.format.DateTimeFormatter.ofPattern(patternDay));
            time = LocalTime.parse(timeString, java.time.format.DateTimeFormatter.ofPattern(patternHour));
        } catch (Exception e) {
            return "There was an error while trying to get the date and time.";
        }

        ZonedDateTime inputDateTime = ZonedDateTime.of(date, time, zoneId);

        long daysDifference = ChronoUnit.DAYS.between(inputDateTime, now);
        long hoursDifference = ChronoUnit.HOURS.between(inputDateTime, now) % 24;

        return "The difference of days: " + daysDifference + ". The difference of hours: " + hoursDifference + ".";
    }

    /**
     * Gets the time zone ID string.
     *
     * @return The time zone
     */
    public String getZoneIdString() {
        return zoneIdString;
    }

    /**
     * Gets the pattern for day formatting.
     *
     * @return The pattern for day formatting.
     */
    public String getPatternDay() {
        return patternDay;
    }

    /**
     * Sets the pattern for day formatting.
     *
     * @param patternDay The pattern for day formatting.
     */
    public void setPatternDay(String patternDay) {
        this.patternDay = patternDay;
    }

    /**
     * Gets the pattern for hour formatting.
     *
     * @return The pattern for hour formatting.
     */
    public String getPatternHour() {
        return patternHour;
    }

    /**
     * Sets the pattern for hour formatting.
     *
     * @param patternHour The pattern for hour formatting.
     */
    public void setPatternHour(String patternHour) {
        this.patternHour = patternHour;
    }

}

