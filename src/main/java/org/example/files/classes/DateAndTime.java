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
import java.util.Comparator;

/**
 * This class deals with everything relative to time & date.
 */
public class DateAndTime {

    String zoneIdString;
    String patternDay;
    String patternHour;

    UserDataSaving dataSaving;

    public DateAndTime(String zoneIdString, String patternDay, String patternHour) {
        this.zoneIdString = zoneIdString;
        this.patternDay = patternDay;
        this.patternHour = patternHour;
    }

    public DateAndTime() {
        this.zoneIdString = "America/New_York";
        this.patternDay = "dd-MM-yyyy";
        this.patternHour = "HH:mm:ss";
    }

    /**
     * This method gets the current date of an individual depending on their time zone
     *
     * @return String date
     */
    public String getDate(String patternDay) {
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternDay);
        return now.format(formatter);
    }

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
     * This method gets the time of an individual depending on their time zone
     *
     * @return String time
     */
    public String getTime(String patternHour) {
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternHour);
        return now.format(formatter);
    }

    /**
     * This method checks whether a date actually exists or not
     *
     * @param dateString    inputted date
     * @return boolean
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

    public String getZoneIdString() {
        return zoneIdString;
    }

    public void setZoneIdString(String Continent, String City, int option) throws IllegalArgumentException {
        int slash = zoneIdString.indexOf('/');
        if (option == 1)
            zoneIdString = Continent + "/" + City;
        else
            zoneIdString = zoneIdString.substring(0,slash) +"/"+ City;
    }


    /**
     * This method checks whether a date is still valid today (has yet to come)
     *
     * @param dateString inputted date
     * @return boolean
     */
    public boolean dateValidToday(String dateString) {
        return getDate(patternDay).equals(dateString);
    }

    /**
     * This method compares a given date and time to the current date and time of the object
     * and returns the numerical difference in days and hours.
     *
     * @param dateString The date to compare in the format of patternDay.
     * @param timeString The time to compare in the format of patternHour.
     * @return String representing the difference in days and hours.
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
            return "Invalid date or time format";
        }

        ZonedDateTime inputDateTime = ZonedDateTime.of(date, time, zoneId);

        long daysDifference = ChronoUnit.DAYS.between(inputDateTime, now);
        long hoursDifference = ChronoUnit.HOURS.between(inputDateTime, now) % 24;

        return "Difference: " + Math.abs(daysDifference) + " days and " + Math.abs(hoursDifference) + " hours";
    }

    public String getPatternDay() {
        return patternDay;
    }

    public void setPatternDay(String patternDay) {
        this.patternDay = patternDay;
    }

    public String getPatternHour() {
        return patternHour;
    }

    public void setPatternHour(String patternHour) {
        this.patternHour = patternHour;
    }
}

class DateComparator implements Comparator<DateAndTime> {
    /**
     * compares the today's date format and the entered date format of the entered date
     * then I have to create a method that will essentially change it to make it the same
     */
    @Override
    public int compare(DateAndTime o1, DateAndTime o2) {
        return o1.getDate(o1.patternDay).compareTo(o2.getDate(o2.patternDay));
    }
}
