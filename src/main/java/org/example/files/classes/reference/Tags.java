package org.example.files.classes.reference;

import java.util.ArrayList;

public class Tags {
    private ArrayList<String> listOfTags;

    public Tags() {
        this.listOfTags = new ArrayList<>();
    }

    public ArrayList<String> getListOfTags() {
        return listOfTags;
    }

    public void setListOfTags(ArrayList<String> listOfTags) {
        this.listOfTags = listOfTags;
    }

    public ArrayList<String> addTags(ArrayList<String> tags) {
        if (tags != null) {
            for (String tag : tags) {
                if (tag != null && !listOfTags.contains(tag.toUpperCase())) {
                    listOfTags.add(tag.toUpperCase());
                }
            }
        }
        return tags;
    }

    public void removeTags(ArrayList<String> tags) {
        if (tags != null) {
            listOfTags.removeAll(tags);
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
