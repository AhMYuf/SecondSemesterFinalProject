package org.example.files.classes.tasks;

import org.example.files.classes.DateAndTime;
import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;

import java.util.ArrayList;

abstract class Task {
    protected LevelOfImportance levelOfImportance;
    protected CompletionStatus completionStatus;
    protected ArrayList<String> listOfTags;

    abstract String setDate(String date);

    abstract void setTime(String start, String end);


}
//TODO add a queue class to add tasks that you want to finish in succession