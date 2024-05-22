package org.example.files.classes;

import java.io.FileOutputStream;

public class UserDataSaving {
    TaskManager taskManager;


    public void writeUserInfo(String input,String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            byte[] bytes = convertToBytes(input);
            fileOutputStream.write(bytes);
        } catch (Exception e) {
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
