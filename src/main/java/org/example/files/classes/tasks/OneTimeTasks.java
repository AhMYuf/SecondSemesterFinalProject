package org.example.files.classes.tasks;

import org.example.files.classes.DateAndTime;

import java.util.ArrayList;
import java.util.List;

public class OneTimeTasks extends Task {
    private static int nextId = 1;
    private String taskId;
    private String taskName;
    private String shortDescription;
    private String date;
    private String startTime;
    private String endTime;
    private String levelOfImportance;
    private String completionStatus;
    protected ArrayList<String> listOfTags;
    public DateAndTime dateAndTime;

    public OneTimeTasks(String taskName, String shortDescription, String endTime, String levelOfImportance, String completionStatus, ArrayList<String> listTag) {
        this.taskId = String.format("D%03d", nextId++);
        this.taskName = taskName;
        this.shortDescription = shortDescription;
        this.date = String.valueOf(dateAndTime.getDate(dateAndTime.getPatternDay()));
        this.startTime = String.valueOf(dateAndTime.getTime(dateAndTime.getPatternHour()));
        this.endTime = endTime;
        this.levelOfImportance = levelOfImportance;
        this.completionStatus = completionStatus;
        this.listOfTags = listTag;
    }


//    public void addTags(String tag, OneTimeTasks oneTimeTasks) {
//        ArrayList<String> temp = oneTimeTasks.getListOfTags();
//
//        if (tag != null && !temp.contains(tag.toUpperCase())) {
//            temp.add(tag.toUpperCase());
//        }
//        oneTimeTasks.setListOfTags(temp);
//    }
//
//    public void removeTags(String tag, OneTimeTasks oneTimeTasks) {
//        ArrayList<String> temp = oneTimeTasks.getListOfTags();
//
//        if (temp != null && tag != null && temp.contains(tag.toUpperCase())) {
//            temp.remove(tag.toUpperCase());
//        }
//        oneTimeTasks.setListOfTags(temp);
//    }

    public boolean tagExists(String tag) {
        List<String> temp = listOfTags;
        if (temp != null && tag != null) {
            return temp.contains(tag.toUpperCase());
        }
        return false;
    }



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
    public String toString() {
        return "OneTimeTasks{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", date='" + date + '\'' +
                ", time='" + startTime + '\'' +
                ", dateAndTime=" + dateAndTime +
                ", levelOfImportance=" + levelOfImportance +
                ", completionStatus=" + completionStatus +
                ", listOfTags=" + listOfTags +
                '}';
    }

    public static int getNextId() {
        return nextId;
    }
    public String getTaskId() {
        return taskId;
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

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(DateAndTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public ArrayList<String> getListOfTags() {
        return listOfTags;
    }

    public void setListOfTags(ArrayList<String> listOfTags) {
        this.listOfTags = listOfTags;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
