package org.example.files.classes;

import org.example.files.classes.tasks.OneTimeTasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    public static boolean createFolder(String path, String folderName) {
        String folderPath = path + File.separator + folderName;
        File folder = new File(folderPath);

        try {
            boolean success = folder.mkdir();
            return success;
        } catch (SecurityException e) {
            System.err.println("SecurityException: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return false;
        }
    }

    public static void createFile(String path, String file) {
        String fileName = String.format("%s/%s.txt", path, file);
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

    public void addTaskToFile(OneTimeTasks task, String path, String fileName) {
        List<OneTimeTasks> tasks = getAllTasks(path, fileName);
        tasks.add(task);
        saveTasks(tasks, path, fileName);
    }

    // Method to remove a task from the file
    public void removeTaskToFile(OneTimeTasks taskToRemove, String path, String fileName) {
        List<OneTimeTasks> tasks = getAllTasks(path, fileName);
        tasks.removeIf(task -> task.equals(taskToRemove));
        saveTasks(tasks, path, fileName);
    }

    // Helper method to load tasks from file
    private List<OneTimeTasks> getAllTasks(String path, String fileName) {
        List<OneTimeTasks> tasks = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "/" + fileName))) {
            tasks = (List<OneTimeTasks>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Helper method to save tasks to file
    private void saveTasks(List<OneTimeTasks> tasks, String path, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "/" + fileName))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
