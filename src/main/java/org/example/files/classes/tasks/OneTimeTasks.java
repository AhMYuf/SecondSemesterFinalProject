package org.example.files.classes.tasks;
import org.example.files.classes.DateAndTime;
import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;
import org.example.files.classes.reference.Tags;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class OneTimeTasks extends Task {
    private static int nextId = 1;
    private String taskId;
    private String taskName;
    private String shortDescription;
    private String date;
    private String time;
    public Tags tags;
    public DateAndTime dateAndTime;

    public OneTimeTasks(String taskName, String shortDescription, LevelOfImportance levelOfImportance, CompletionStatus completionStatus, ArrayList<String> listTag) {
        this.taskId = String.format("D%03d", nextId++);
        this.taskName = taskName;
        this.shortDescription = shortDescription;
        this.date = String.valueOf(dateAndTime.getDate());
        this.time = String.valueOf(dateAndTime.getTime());
        this.levelOfImportance = levelOfImportance;
        this.completionStatus = completionStatus;
        this.listOfTags = tags.addTags(listTag);
    }

    public OneTimeTasks() {
        //empty constructor
    }



//    public void addingTags(String tag) {
//        if (tag == null || tag.isEmpty()) {
//            System.out.println("Invalid tag. Please provide a non-empty tag.");
//            return;
//        }
//        if (listOfTags.contains(tag)) {
//            System.out.println("The tag already exists.");
//        } else {
//            listOfTags.add(tag);
//            System.out.println("Tag added successfully!");
//        }
//    }

    @Override
    public String setDate(String date) {
        if (dateAndTime.dateExists(date)) {
            if (dateAndTime.dateValidToday(date)) {
                this.date = date;
                return "Date set successfully.";
            } else {
                return "The date has already passed.";
            }
        } else {
            return "The entered date does not exist.";
        }
    }

    @Override
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


    public void removingTag(String tag) { // this code has to also remove the entered tag into a backup file
        if (listOfTags.isEmpty()) {
            System.out.println("There are no tags to be removed.");
            return;
        }

        boolean removed = false;
        Iterator<String> iterator = listOfTags.iterator();
        while (iterator.hasNext()) {
            String currentTag = iterator.next();
            if (tag.equalsIgnoreCase(currentTag)) {
                iterator.remove();
                removed = true;
            }
        }
        if (removed) {
            System.out.println("Tag removed successfully!");
        } else {
            System.out.println("The tag could not be removed as it does not exist.");
        }
    }

    @Override
    public String toString() {
        return "OneTimeTasks{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", dateAndTime=" + dateAndTime +
                ", levelOfImportance=" + levelOfImportance +
                ", completionStatus=" + completionStatus +
                ", listOfTags=" + listOfTags +
                '}';
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public LevelOfImportance getLevelOfImportance() {
        return levelOfImportance;
    }

    public void setLevelOfImportance(LevelOfImportance levelOfImportance) {
        this.levelOfImportance = levelOfImportance;
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    public ArrayList<String> getListOfTags() {
        return listOfTags;
    }

    public void setListOfTags(ArrayList<String> listOfTags) {
        this.listOfTags = listOfTags;
    }


}
