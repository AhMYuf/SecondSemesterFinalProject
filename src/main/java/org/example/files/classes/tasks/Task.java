package org.example.files.classes.tasks;

import org.example.files.classes.DateAndTime;
import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;

import java.util.ArrayList;

abstract class Task {
    protected LevelOfImportance levelOfImportance;
    protected CompletionStatus completionStatus;
    protected ArrayList<String> listOfTags;

    abstract void removingTag(String tag);

    abstract void addingTags(String tag);

    abstract String setDate(String date, String pattern);

    abstract void setTime(String start, String end);


}
