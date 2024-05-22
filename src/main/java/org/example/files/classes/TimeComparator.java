package org.example.files.classes;

import org.example.files.classes.tasks.OneTimeTasks;

import java.util.Comparator;

/**
 * Comparator for comparing OneTimeTasks objects based on their start times.
 */
public class TimeComparator implements Comparator<OneTimeTasks> {

    /**
     * Compares the start times of two {@link OneTimeTasks} objects.
     *
     * @param o1 the first OneTimeTasks object
     * @param o2 the second OneTimeTasks object
     * @return a negative integer, zero, or a positive integer as the first OneTimeTasks object is less than, equal to, or greater than the second OneTimeTasks object
     */
    @Override
    public int compare(OneTimeTasks o1, OneTimeTasks o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}
