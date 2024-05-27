package org.example.files.classes;

import org.example.files.classes.tasks.OneTimeTasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages tasks by providing methods to create folders, files, add tasks to files, and remove tasks from files.
 */
public class TaskManager {

    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());

    /**
     * Creates a folder at the specified path with the given folder name.
     *
     * @param path       The path where the folder will be created.
     * @param folderName The name of the folder to be created.
     * @return true if the folder creation is successful, false otherwise.
     */
    public static boolean createFolder(String path, String folderName) { // "/Users/ahmetyusufyildirim/Desktop";
        String folderPath = path + File.separator + folderName;
        File folder = new File(folderPath);

        try {
            return folder.mkdir();
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "SecurityException occurred while creating folder: " + folderPath, e);
            return false;
        }
    }

    /**
     * Creates a file at the specified path with the given file name.
     *
     * @param path The path where the file will be created.
     * @param fileName The name of the file to be created.
     */
    public static boolean createFile(String path, String fileName) {
        String filePath = path + File.separator + fileName;
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                logger.info("File created successfully: " + filePath);
                return true;
            } else {
                logger.info("File already exists: " + filePath);
                return false;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while creating file: " + filePath, e);
            return false;
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
    private static List<OneTimeTasks> getAllTasks(String path, String fileName) {
        List<OneTimeTasks> tasks = new ArrayList<>();
        String filePath = path + File.separator + fileName;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            tasks = (List<OneTimeTasks>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error occurred while reading tasks from file: " + filePath, e);
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
    private static void saveTasks(List<OneTimeTasks> tasks, String path, String fileName) {
        String filePath = path + File.separator + fileName;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while saving tasks to file: " + filePath, e);
        }
    }
}
