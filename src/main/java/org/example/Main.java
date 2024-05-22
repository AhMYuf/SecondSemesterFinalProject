package org.example;


import org.example.files.classes.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose one of the option: '1' for entering personal information, '2' for running the demo");
        int choice = scanner.nextInt();
        if (choice == 1) {
            userInterface.gettingUserData();
        } else {
            userInterface.loop();
        }
    }
}