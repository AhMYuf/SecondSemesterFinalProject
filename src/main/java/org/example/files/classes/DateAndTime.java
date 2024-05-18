package org.example.files.classes;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * This class deals with everything relative to time & date.
 */
public class DateAndTime implements Comparable<DateAndTime> {

    String zoneIdString = "America/New_York";
    String patternDay = "dd-MM-yyyy";
    String patternHour = "HH:mm:ss";

    /**
     * This method gets the current date of an individual depending on their time zone
     *
     * @return String date
     */
    public String getDate() {
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        return String.valueOf(now.toLocalDate());
    }

    /**
     * This method gets the time of an individual depending on their time zone
     *
     * @return String time
     */
    public String getTime() {
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        return String.valueOf(now.toLocalTime().truncatedTo(ChronoUnit.SECONDS));
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

    public void setZoneIdString(String Continent, String City, int option) {
        int slash = zoneIdString.indexOf('/');
        if (option == 1)
            zoneIdString = Continent + "/" + City;
        else if (option == 2)
            zoneIdString = Continent + zoneIdString.substring(slash);
        else
            zoneIdString = zoneIdString.substring(0,slash) +"/"+ City;
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

    /**
     * This method checks whether a date is still valid today (has yet to come)
     *
     * @param dateString inputted date
     * @return boolean
     */
    public boolean dateValidToday(String dateString) {
        return getDate().equals(dateString);
    }

    @Override
    public int compareTo(DateAndTime o) {
        LocalDate day = LocalDate.now();
        ZoneId zoneId = ZoneId.of(this.zoneIdString);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        long difference = ChronoUnit.HOURS.between(day, now);
        return (int) difference;
    }
}

class DateComparator implements Comparator<DateAndTime> {
    /**
     * compares the today's date format and the entered date format of the entered date
     * then I have to create a method that will essentially change it to make it the same
     */
    @Override
    public int compare(DateAndTime o1, DateAndTime o2) {
        return o1.getDate().compareTo(o2.getDate());
    }

}
