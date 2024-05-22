package org.example.files.classes;

import org.example.files.classes.tasks.OneTimeTasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private ArrayList<OneTimeTasks> oneTimeTasks;
    private FileInputStream fInput;
    private FileOutputStream fOut;
    public String nameOfFolder;

    public TaskManager(String nameOfFolder) {
        this.nameOfFolder = nameOfFolder;
        createFolder();
    }

    private void createFolder() {
        File folder = new File(nameOfFolder);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Folder created successfully");
            } else {
                System.out.println("Failed to create folder");
            }
        }
    }

    public void createFile(String file) {
        String fileName = String.format("%s/%s.txt", nameOfFolder, file);
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

    // TODO add the task to the createdTasks
    public void addTaskToFile(OneTimeTasks task, String fileName) {
        List<OneTimeTasks> tasks = getAllTasks(fileName);
        tasks.add(task);
        saveTasks(tasks, fileName);
    }

    // Method to remove a task from the file
    public void removeTaskToFile(OneTimeTasks taskToRemove, String fileName) {
        List<OneTimeTasks> tasks = getAllTasks(fileName);
        tasks.removeIf(task -> task.equals(taskToRemove));
        saveTasks(tasks, fileName);
    }


    // Method to display all tasks
    public void displayingTasks(String FILE_NAME) {
        List<OneTimeTasks> tasks = getAllTasks(FILE_NAME);
        for (OneTimeTasks task : tasks) {
            System.out.println(task);
        }
    }

    // Helper method to load tasks from file
    private List<OneTimeTasks> getAllTasks(String fileName) {
        List<OneTimeTasks> tasks = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameOfFolder + "/" + fileName))) {
            tasks = (List<OneTimeTasks>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Helper method to save tasks to file
    private void saveTasks(List<OneTimeTasks> tasks, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nameOfFolder + "/" + fileName))) {
            oos. writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNameOfFolder() {
        return nameOfFolder;
    }

    public void setNameOfFolder(String nameOfFolder) {
        this.nameOfFolder = nameOfFolder;
    }
}
