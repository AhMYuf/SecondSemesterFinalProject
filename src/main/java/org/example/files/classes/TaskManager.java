package org.example.files.classes;

import org.example.files.classes.tasks.OneTimeTasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages tasks by providing methods to create folders, files, add tasks to files, and remove tasks from files.
 */
public class TaskManager {

    /**
     * Creates a folder at the specified path with the given folder name.
     *
     * @param path       The path where the folder will be created.
     * @param folderName The name of the folder to be created.
     * @return true if the folder creation is successful, false otherwise.
     */
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

    /**
     * Creates a file at the specified path with the given file name.
     *
     * @param path The path where the file will be created.
     * @param file The name of the file to be created.
     */
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

    /**
     * Adds a task to the specified file.
     *
     * @param task     The task to be added.
     * @param path     The path where the file is located.
     * @param fileName The name of the file to which the task will be added.
     */
    public void addTaskToFile(OneTimeTasks task, String path, String fileName) {
        List<OneTimeTasks> tasks = getAllTasks(path, fileName);
        tasks.add(task);
        saveTasks(tasks, path, fileName);
    }

    /**
     * Removes a task from the specified file.
     *
     * @param taskToRemove The task to be removed.
     * @param path         The path where the file is located.
     * @param fileName     The name of the file from which the task will be removed.
     */
    public void removeTaskToFile(OneTimeTasks taskToRemove, String path, String fileName) {
        List<OneTimeTasks> tasks = getAllTasks(path, fileName);
        tasks.removeIf(task -> task.equals(taskToRemove));
        saveTasks(tasks, path, fileName);
    }

    /**
     * Loads tasks from the specified file.
     *
     * @param path     The path where the file is located.
     * @param fileName The name of the file from which tasks will be loaded.
     * @return The list of tasks loaded from the file.
     */
    private List<OneTimeTasks> getAllTasks(String path, String fileName) {
        List<OneTimeTasks> tasks = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "/" + fileName))) {
            tasks = (List<OneTimeTasks>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Saves tasks to the specified file.
     *
     * @param tasks    The list of tasks to be saved.
     * @param path     The path where the file is located.
     * @param fileName The name of the file to which tasks will be saved.
     */
    private void saveTasks(List<OneTimeTasks> tasks, String path, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "/" + fileName))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
