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
import java.util.Date;

/**
 * This class handles operations related to date and time.
 */
public class DateAndTime {
    /**
     * The time zone ID string.
     */
    private String zoneIdString;

    /**
     * The pattern for day formatting.
     */
    private String patternDay;

    /**
     * The pattern for hour formatting.
     */
    private String patternHour;


    /**
     * No parameter constructor
     */
    public DateAndTime() {
        this.zoneIdString = "America/New_York";
        this.patternDay = "dd-MM-yyyy";
        this.patternHour = "HH:mm:ss";
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
     * @return The current time.
     */
    public String getTime() {
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getPatternHour());
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
     * Checks if the given time string contains valid time values.
     *
     * @param time A string representing time in the format "HH:mm:ss".
     * @return true if the time values are valid, false otherwise.
     */
    public static boolean isValidTimeValues(String time) {
        try {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);

            if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60 && seconds >= 0 && seconds < 60) {
                return true;
            } else {
                System.out.println("Invalid time values. Hours must be between 0 and 23, minutes and seconds between 0 and 59.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid time values. Please ensure hours, minutes, and seconds are numeric.");
            return false;
        }
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateString);
            Date today = new Date(); // Get today's date
            return date.compareTo(today) >= 0; // Compare the entered date with today's date
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }    }

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

        long minutesDifference = ChronoUnit.MINUTES.between(now, inputDateTime);
        long absMinutesDifference = Math.abs(minutesDifference);

        long totalHours = absMinutesDifference / 60;
        long remainingMinutes = absMinutesDifference % 60;

        return "The difference in days: " + ChronoUnit.DAYS.between(now.toLocalDate(), date) + ".In terms of time it is, hours: " + totalHours + ", and minutes: " + remainingMinutes;

    }

    /**
     * Checks if the given date string is in a valid date format "dd-MM-yyyy".
     *
     * @param date A string representing a date.
     * @return true if the date string is in a valid format, false otherwise.
     */
    public static boolean isValidDateFormat(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd-MM-yyyy.");
            return false;
        }
    }

    /**
     * Checks if the given time string is in a valid time format "HH:mm:ss".
     *
     * @param time A string representing a time.
     * @return true if the time string is in a valid format, false otherwise.
     */
    public static boolean isValidTimeFormat(String time) {
        if (time.length() != 8) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setLenient(false);
            sdf.parse(time);
            return true;
        } catch (ParseException e) {
            return false; // Parsing failed, invalid time format
        }
    }

    /**
     * Checks if the given date string is not null or empty.
     *
     * @param date A string representing a date.
     * @return true if the date string is not null or empty, false otherwise.
     */
    public static boolean dateNotNull(String date) {
        return date != null && !date.isEmpty();
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

    public static void main(String[] args) {
        System.out.println(isValidTimeFormat("12:34:56"));
    }
}

