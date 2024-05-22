package org.example.files.classes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserDataSaving {
    TaskManager taskManager;

    public String nameFolder = taskManager.getNameOfFolder() + "/userData.txt";

    public void writeUserInfo(String input) throws FileNotFoundException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(nameFolder)) {
            byte[] bytes = convertToBytes(input);
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.getMessage();
        }
    }

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
