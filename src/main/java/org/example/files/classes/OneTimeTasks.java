package org.example.files.classes;

import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;

import java.util.ArrayList;
import java.util.Iterator;

public class OneTimeTasks {
    private static int nextId = 1;
    private String taskId;
    private String taskName;
    private String shortDescription;
    private LevelOfImportance levelOfImportance;
    private CompletionStatus completionStatus;
    private ArrayList<String> listOfTags;

    public OneTimeTasks(String taskName, String shortDescription, LevelOfImportance levelOfImportance, CompletionStatus completionStatus, ArrayList<String> listOfTags) {
        this.taskId = String.format("D%03d", nextId++);
        this.taskName = taskName;
        this.shortDescription = shortDescription;
        this.levelOfImportance = levelOfImportance;
        this.completionStatus = completionStatus;
        this.listOfTags = listOfTags;
    }

    public OneTimeTasks() {
        //empty constructor
    }

    public void addingTags(String tag) { // this code has to also add the entered tag into a backup file
        if (tag == null || tag.isEmpty()) {
            System.out.println("Invalid tag. Please provide a non-empty tag.");
            return;
        }

        if (listOfTags.contains(tag)) {
            System.out.println("The tag already exists.");
        } else {
            listOfTags.add(tag);
            System.out.println("Tag added successfully!");
        }
    }

    public void removingTags(String tag) { // this code has to also remove the entered tag into a backup file
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
