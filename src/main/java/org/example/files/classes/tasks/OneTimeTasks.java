package org.example.files.classes.tasks;

import org.example.files.classes.DateAndTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a one-time task.
 */
public class OneTimeTasks extends Task {
    private static int nextId = 1;
    private String taskId;
    private String taskName;
    private String shortDescription;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String levelOfImportance;
    private String completionStatus;
    protected ArrayList<String> listOfTags;
    private DateAndTime dateAndTime;


    /**
     * Constructs a new one-time task with the given parameters.
     *
     * @param taskName          the name of the task
     * @param shortDescription  a short description of the task
     * @param endTime           the end time of the task
     * @param endDate           the end date of the task
     * @param levelOfImportance the level of importance of the task
     * @param completionStatus  the completion status of the task
     * @param listTag           the list of tags associated with the task
     */
    public OneTimeTasks(String taskName, String shortDescription, String endTime, String endDate, String levelOfImportance, String completionStatus, ArrayList<String> listTag, DateAndTime dateAndTime) {
        this.taskId = String.format("D%03d", nextId++);
        this.taskName = taskName;
        this.shortDescription = shortDescription;
        this.dateAndTime = dateAndTime;  // Initialize the dateAndTime field
        this.startDate = dateAndTime.getDate(dateAndTime.getZoneIdString());
        this.endDate = endDate;
        this.startTime = dateAndTime.getTime();
        this.endTime = endTime;
        this.levelOfImportance = levelOfImportance;
        this.completionStatus = completionStatus;
        this.listOfTags = listTag;
    }


    /**
     * Gets the next available task ID.
     *
     * @return the next available task ID
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Checks if the given tag exists in the list of tags.
     *
     * @param tag the tag to check
     * @return true if the tag exists, false otherwise
     */
    public void tagExists(String tag) {
        List<String> temp = listOfTags;
        if (temp != null && tag != null) {
            if (temp.contains(tag.toUpperCase())) {
                System.out.println("Tag " + tag + " exists.");
            } else {
                System.out.println("Tag " + tag + " does not exist.");
            }
        } else {
            System.out.println("Tag " + tag + " does not exist.");
        }
    }

    /**
     * Sets the start date of the task.
     *
     * @param date the start date to set
     * @return a message indicating the result of the operation
     */
    @Override
    public String setDate(String date) {
        if (dateAndTime.dateExists(date)) {
            if (dateAndTime.dateValidToday(date)) {
                this.startDate = date;
                return "Date set successfully.";
            } else {
                return "The date has already passed.";
            }
        } else {
            return "The entered date does not exist.";
        }
    }

    /**
     * Returns a string representation of the one-time task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "OneTimeTasks{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", date='" + startDate + '\'' +
                ", time='" + startTime + '\'' +
                ", levelOfImportance=" + levelOfImportance +
                ", completionStatus=" + completionStatus +
                ", listOfTags=" + listOfTags +
                '}';
    }

    /**
     * Gets the level of importance of the task.
     *
     * @return the level of importance
     */
    public String getLevelOfImportance() {
        return levelOfImportance;
    }

    /**
     * Sets the level of importance of the task.
     *
     * @param levelOfImportance the level of importance to set
     */
    public void setLevelOfImportance(String levelOfImportance) {
        this.levelOfImportance = levelOfImportance;
    }

    /**
     * Gets the task ID.
     *
     * @return the task ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Gets the task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the task name.
     *
     * @param taskName the task name to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Gets the short description of the task.
     *
     * @return the short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the short description of the task.
     *
     * @param shortDescription the short description to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Gets the start date of the task.
     *
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the task.
     *
     * @param startDate the start date to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the start time of the task.
     *
     * @return the start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the task.
     *
     * @param startTime the start time to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the date and time object associated with the task.
     *
     * @return the date and time object
     */
    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Gets the list of tags associated with the task.
     *
     * @return the list of tags
     */
    public ArrayList<String> getListOfTags() {
        return listOfTags;
    }

    /**
     * Sets the list of tags associated with the task.
     *
     * @param listOfTags the list of tags to set
     */
    public void setListOfTags(ArrayList<String> listOfTags) {
        this.listOfTags = listOfTags;
    }

    /**
     * Gets the end time of the task.
     *
     * @return the end time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the task.
     */


    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the end date of the task.
     *
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the task.
     *
     * @param endDate the end date to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return The completion status of the task.
     */
    public String getCompletionStatus() {
        return completionStatus;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param completionStatus The completion status to be set.
     */
    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }
}


