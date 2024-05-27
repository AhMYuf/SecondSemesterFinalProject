package org.example;


import org.example.files.classes.DateAndTime;
import org.example.files.classes.UserInterface;

import java.util.Scanner;

/**
 * This class runs the user interactive methods.
 */
public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        System.out.println("If this is your first time using, please first create an folder and file to store all your tasks (minimum).");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose one of the option: '1' for entering personal information, '2' for running the demo");
        int choice = scanner.nextInt();
        if (choice == 1) {
            userInterface.gettingUserData(new DateAndTime());
            userInterface.loop();
        } else {
            userInterface.loop();
        }
    }
}
