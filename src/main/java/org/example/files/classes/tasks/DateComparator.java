package org.example.files.classes.tasks;

import java.util.Comparator;

/**
 * Comparator for comparing {@link OneTimeTasks} objects based on their start dates.
 */
public class DateComparator implements Comparator<OneTimeTasks> {

    /**
     * Compares the start dates of two OneTimeTasks objects.
     *
     * @param o1 the first OneTimeTasks object
     * @param o2 the second OneTimeTasks object
     * @return a negative integer, zero, or a positive integer as the first OneTimeTasks object is less than, equal to, or greater than the second OneTimeTasks object
     */
    @Override
    public int compare(OneTimeTasks o1, OneTimeTasks o2) {
        return o1.getStartDate().compareTo(o2.getStartDate());
    }
}
