package org.example.files.classes;

import java.io.FileOutputStream;

/**
 * This class is responsible for writing user information to a file.
 */
public class UserDataSaving {
    TaskManager taskManager; // Instance of TaskManager (not currently used)

    /**
     * Writes user information to the specified file.
     *
     * @param input    The user information to be written.
     * @param filePath The path of the file where the information will be written.
     */
    public void writeUserInfo(String input, String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            byte[] bytes = convertToBytes(input);
            fileOutputStream.write(bytes);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Converts the input object to bytes.
     *
     * @param input The input object to be converted.
     * @return The byte array representation of the input.
     * @throws IllegalArgumentException if the input type is unsupported.
     */
    private byte[] convertToBytes(Object input) {
        if (input instanceof Integer) {
            return Integer.toString((int) input).getBytes();
        } else if (input instanceof String) {
            return ((String) input).getBytes();
        } else if (input instanceof Character) {
            return new byte[]{(byte) ((char) input)};
        } else {
            throw new IllegalArgumentException("Unsupported input type: " + input.getClass());
        }
    }
}
