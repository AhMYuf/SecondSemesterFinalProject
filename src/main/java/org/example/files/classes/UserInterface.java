package org.example.files.classes;

import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;
import org.example.files.classes.tasks.OneTimeTasks;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    DateAndTime dateAndTime;
    TaskManager taskManager;

    ArrayList<OneTimeTasks> createdTasks;

    public void loop() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String date;
        String time;
        String name;

        while (!userInput.equals("STOP")) {
            System.out.println("Please enter the number of action you wish to perform: ");
            userInput = scanner.nextLine().toLowerCase();

            switch (userInput) {
                case "0":
                    System.out.println("Please enter '1' for default settings, and '2' for personal preference: ");
                    int num = scanner.nextInt();
                    if (num == 1) {
                        DateAndTime dateAndTime = new DateAndTime();
                    } else {
                        System.out.println("Please enter your zoneId: ");
                        String zone = scanner.nextLine();
                        System.out.println("Please enter your day pattern: ");
                        String dayPattern = scanner.nextLine();
                        System.out.println("Please enter your time pattern: ");
                        String timePattern = scanner.nextLine();
                        DateAndTime dateAndTime = new DateAndTime(zone, dayPattern, timePattern);
                        System.out.println("Creating a new date object ... ");
                    }
                    break;
                case "1":
                    date = dateAndTime.getDate(dateAndTime.getPatternDay());
                    System.out.println("Today is: " + date);
                    break;
                case "2":
                    time = dateAndTime.getTime(dateAndTime.getPatternHour());
                    System.out.println("The current time is: " + time);
                case "3":
                    System.out.println("Please enter the date in a valid formet (dd-MM-yyyy): ");
                    date = scanner.nextLine();
                    System.out.println("The entered date is: " + dateAndTime.dateExists(date));
                    break;
                case "4":
                    System.out.println("Enter a date in a valid formet (dd-MM-yyyy):: ");
                    date = scanner.nextLine();
                    System.out.println("The entered date is: " + dateAndTime.dateValidToday(date));
                    break;
                case "5":
                    System.out.println("Please enter a Continent: ");
                    String continent = scanner.nextLine();
                    System.out.println("Please enter a city: ");
                    String city = scanner.nextLine();
                    System.out.println("Please enter chose your option:" + "1 = Setting the new zone according to the inputted continent and city" + "2 = Changing the city only");
                    int option = scanner.nextInt();
                    dateAndTime.setZoneIdString(continent, city, option);
                    break;
                case "6":
                    System.out.println(dateAndTime.getZoneIdString());
                    break;
                case "7":
                    System.out.println("Please enter the date: ");
                    date = scanner.nextLine();
                    System.out.println("Please enter the time: ");
                    time = scanner.nextLine();
                    dateAndTime.compareDateTime(date, time);
                    break;
                case "8":
                    System.out.println("The day pattern is: " + dateAndTime.getPatternDay());
                    break;
                case "9":
                    System.out.println("The time pattern is: " + dateAndTime.getPatternHour());
                    break;
                case "10":
                    System.out.println("Please enter new date pattern: ");
                    date = scanner.nextLine();
                    dateAndTime.setPatternDay(date);
                    break;
                case "11":
                    System.out.println("Please enter new time pattern: ");
                    time = scanner.nextLine();
                    dateAndTime.setPatternHour(time);
                    break;
                case "12":
                    System.out.println("Please enter the name of the folder: ");
                    name = scanner.nextLine();
                    taskManager = new TaskManager(name);
                    break;
                case "13":
                    System.out.println("Please enter the name of the file: ");
                    name = scanner.nextLine();
                    taskManager.createFile(name);
                case "14":
                    System.out.println("Please enter the required information for your task: ");

                    System.out.println("Task name: ");
                    String taskName = scanner.nextLine();

                    System.out.println("Task name: ");
                    String description = scanner.nextLine();

                    System.out.println("Task name: ");
                    String endTime = scanner.nextLine();

                    System.out.println("Enter the number corresponding to the level of importance:");
                    for (int i = 0; i < LevelOfImportance.values().length; i++) {
                        System.out.println((i + 1) + ". " + LevelOfImportance.values()[i]);
                    }

                    System.out.print("Enter your choice: ");
                    num = scanner.nextInt();

                    String levelOfImp = (num >= 1 && num <= LevelOfImportance.values().length) ? LevelOfImportance.values()[num - 1].toString() : "Unknown";

                    System.out.println("Enter the number corresponding to the level of importance:");
                    for (int i = 0; i < CompletionStatus.values().length; i++) {
                        System.out.println((i + 1) + ". " + CompletionStatus.values()[i]);
                    }

                    System.out.print("Enter your choice: ");
                    num = scanner.nextInt();

                    String CompStat = (num >= 1 && num <= CompletionStatus.values().length) ? CompletionStatus.values()[num - 1].toString() : "Unknown";

                    System.out.println("Enter the number of tags: ");
                    num = scanner.nextInt();

                    ArrayList<String> temp = new ArrayList<>();
                    for (int i = 0; i < num; i++) {
                        System.out.println("Enter the tag(s): ");
                        String tag = scanner.nextLine();
                        temp.add(tag);
                    }

                    createdTasks.add(new OneTimeTasks(taskName, description, endTime, levelOfImp, CompStat, temp));

                case 15:
                    System.out.print("Enter tag to add: ");
                    String tagToAdd = scanner.nextLine();

                    for (OneTimeTasks item : createdTasks) {
                        System.out.println(item);
                    }
                    System.out.println("Please give the ID of task you wish to add: ");
                    name = scanner.nextLine();
                    for (OneTimeTasks item : createdTasks) {
                        if (item.getTaskId().equals(name)) {
                            item.getListOfTags().add(" " + tagToAdd.toUpperCase());
                        }
                    }
                    break;
                case 16:
                    System.out.print("Enter tag to remove: ");
                    String tagToRemove = scanner.nextLine();

                    for (OneTimeTasks item : createdTasks) {
                        System.out.println(item);
                    }
                    System.out.println("Please give the ID of task you wish to remove: ");
                    name = scanner.nextLine();
                    for (OneTimeTasks item : createdTasks) {
                        if (item.getTaskId().equals(name)) {
                            item.getListOfTags().remove(tagToRemove.toUpperCase());
                        }
                    }
                    break;
                case 17:
                    ArrayList<String> display = new ArrayList<>();
                    for (OneTimeTasks item : createdTasks) {
                        display.add(String.valueOf(item.getListOfTags()));
                    }
                case 18:
                    System.out.print("Enter tag to check: ");
                    String tagToCheck = scanner.nextLine().toUpperCase();

                    for (OneTimeTasks item : createdTasks) {
                        item.tagExists(tagToCheck);
//                        for (int i = 0; i < item.getListOfTags().size(); i++) {
//                            if (item.getListOfTags().equals(tagToCheck)) {
//                                System.out.println("The entered tag exist.");
//                            } else {
//                                System.out.println("Tag does not exist.");
//                            }
//                        }
                    }
                    break;
                case 19:
                    System.out.print("Enter date to set: ");
                    String dateToSet = scanner.nextLine();

                    System.out.println("Enter the task name you wish to modify: ");
                    name = scanner.nextLine();

                    for (OneTimeTasks item : createdTasks) {
                        if (item.getTaskId().equals(name)) {
                            item.setDate(dateToSet);
                        } else {
                            System.out.println("There is no task with the entered ID.");
                        }
                    }
                    break;
                case 20:
                    System.out.print("Enter start time (HH:mm:ss): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (HH:mm:ss): ");
                    endTime = scanner.nextLine();
                    dateAndTime.setTime(startTime, endTime);
                    break;

                case 22:
                    System.out.println("Next ID: " + OneTimeTasks.getNextId());
                    // TODO this will be saved
                    break;
                case 24:
                    System.out.println("Please enter the index of element you wish to get the ID: ");
                    num = scanner.nextInt();
                    System.out.println("Task ID: " + createdTasks.get(num).getTaskId());
                    break;
                case 26:
                    System.out.println("Please enter the index of element you wish to get the ID: ");
                    num = scanner.nextInt();
                    System.out.println("Task Name: " + createdTasks.get(num).getTaskName());
                    break;
                case 27:
                    System.out.println("Enter the index of the task you wish to modify the name: ");
                    num = scanner.nextInt();

                    System.out.print("Enter new task name: ");
                    taskName = scanner.nextLine();
                    createdTasks.get(num).setTaskName(taskName);
                    System.out.println("Task Name: " + taskName);
                    break;
                case 28:
                    System.out.println("Enter the index of the task you wish to modify the name: ");
                    num = scanner.nextInt();
                    System.out.println("Short Description: " + createdTasks.get(num).getShortDescription());
                    break;
                case 29:
                    System.out.println("Enter the index of the task you wish to modify the name: ");
                    num = scanner.nextInt();

                    System.out.print("Enter new short description: ");
                    String shortDescription = scanner.nextLine();
                    createdTasks.get(num).setShortDescription(shortDescription);
                    System.out.println("Short Description entered.");
                    break;

                case 30:
                    System.out.println("Enter the index of the task you wish to get the starting time: ");
                    num = scanner.nextInt();
                    System.out.println("Start Time: " + createdTasks.get(num).getStartTime());
                    break;
                case 31:
                    System.out.println("Enter the index of the task you wish to set the starting time: ");
                    num = scanner.nextInt();


                    System.out.println("Start Time: " + createdTasks.get(num).setStartTime());
                    break;
                case 32:
                    System.out.print("Enter new start time: ");
                    String newStartTime = scanner.nextLine();
                    oneTimeTasks.setStartTime(newStartTime);
                    break;
                case 33:
                    System.out.println("DateAndTime: " + oneTimeTasks.getDateAndTime());
                    break;
                case 34:
                    // Assuming you have a method to input a DateAndTime object
                    // DateAndTime newDateAndTime = inputDateAndTime(scanner);
                    // oneTimeTasks.setDateAndTime(newDateAndTime);
                    break;
                case 35:
                    System.out.println("List of Tags: " + oneTimeTasks.getListOfTags());
                    break;
                case 36:
                    // Assuming you have a method to input a list of tags
                    // ArrayList<String> newTags = inputTags(scanner);
                    // oneTimeTasks.setListOfTags(newTags);
                    break;
                case 37:
                    System.out.println("End Time: " + oneTimeTasks.getEndTime());
                    break;
                case 38:
                    System.out.print("Enter new end time: ");
                    String newEndTime = scanner.nextLine();
                    oneTimeTasks.setEndTime(newEndTime);
                    break;
                default:
                    System.out.println("Unknown command: " + userInput);
                    break;
            }
            scanner.close();
        }
    }
}

