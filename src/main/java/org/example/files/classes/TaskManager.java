package org.example.files.classes;

import org.example.files.classes.tasks.OneTimeTasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskManager implements Comparator<OneTimeTasks> {
    private ArrayList<OneTimeTasks> oneTimeTasks;
    private FileInputStream fInput;
    private FileOutputStream fOut;

    private String nameOfFolder;


    public void creatFolder(String name) {
        this.nameOfFolder = name;
        File folder = new File(name);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created successfully");
            } else {
                System.out.println("Failed to create folder");
            }
        }
    }

    public void createFile(String name) {
        String fileName = String.format("%s/%s.txt", nameOfFolder, name);
        try {
            File file1 = new File(fileName);
            if (file1.createNewFile()) {
                System.out.println("File1 created successfully");
            } else {
                System.out.println("File1 already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating files");
            e.printStackTrace();
        }
    }

    public void addTask() {

    }

    public void removeTask() {

    }

    public void completedTask() {

    }

    public void displayingTasks() {

    }

    public void savingTaskToFile() {

    }

    public void removingTaskToFile() {

    }

    @Override
    public int compare(OneTimeTasks o1, OneTimeTasks o2) {
        return 0;
    }
}
