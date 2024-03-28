package org.example.files.classes;
import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;

import java.util.ArrayList;
abstract class Task {

    private static int nextId = 1;
    private String taskId;
    private String taskName;
    private String shortDescription;
    private LevelOfImportance levelOfImportance;
    private CompletionStatus completionStatus;
    private ArrayList<String> listOfTags;

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Task.nextId = nextId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
