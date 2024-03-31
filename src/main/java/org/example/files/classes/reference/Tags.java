package org.example.files.classes.reference;

import java.util.ArrayList;

public class Tags {

    private ArrayList<String> listOfTags;

//    public Tags() {
//        listOfTags = new ArrayList<>();
//    }

    public void addTag(String tag) {
        if (tag != null && !listOfTags.contains(tag.toUpperCase())) {
            listOfTags.add(tag.toUpperCase());
        }
    }

    public void removeTag(String tag) {
        if (!listOfTags.isEmpty()) {
            listOfTags.remove(tag.toUpperCase());
        } else {
            System.out.println("There are no tags to be removed");
        }
    }

    public void displayTags() {
        System.out.print("[ ");
        for (String tag : listOfTags) {
            System.out.print(tag + " ");
        }
        System.out.print("] ");
    }

    public boolean tagExists(String tag) {
        return listOfTags.contains(tag.toUpperCase());
    }

    public int countTags() {
        return listOfTags.size();
    }
}
