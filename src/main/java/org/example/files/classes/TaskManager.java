package org.example.files.classes;

import org.example.files.classes.tasks.OneTimeTasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
        String filePath = path + File.separator + fileName;
        Path fPath = Paths.get(filePath);
        try {
            appendToFile(fPath,task.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Tasks after adding: " + task);
    }

    /**
     * Removes a task from the specified file.
     *
     * @param tasks       The list of tasks from which the task will be removed.
     * @param taskToRemove The task to be removed.
     * @param path         The path where the file is located.
     * @param fileName     The name of the file from which the task will be removed.
     */
    public void removeTaskToFile(List<OneTimeTasks> tasks, OneTimeTasks taskToRemove, String path, String fileName) {
        String filePath = path + File.separator + fileName;

        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path fPath = Paths.get(filePath);
        tasks.remove(taskToRemove);

        for (OneTimeTasks task: tasks) {
            try {
                appendToFile(fPath, task.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
       logger.info("Tasks after removing: " + tasks);
    }

    /**
     * Appends content to a file located at the specified path.
     *
     * @param path     The path where the file is located.
     * @param content  The content to be appended to the file.
     * @throws IOException If an I/O error occurs while appending to the file.
     */
    private static void appendToFile(Path path, String content)
            throws IOException {
        Files.write(path, content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
}
