package org.example;


import org.example.files.classes.UserInterface;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.gettingUserData();
        userInterface.loop();
    }
}