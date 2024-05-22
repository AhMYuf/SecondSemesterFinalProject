package org.example.files.classes.tasks;

import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;

abstract class Task {
    protected LevelOfImportance levelOfImportance;
    protected CompletionStatus completionStatus;
    abstract String setDate(String date);

    /*
     * Normally there was going to be multiple variations of tasks, but due to a file saving issue, I lost all progress
     * causing me to rush the project.
     */
}
