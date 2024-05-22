package org.example.files.classes;

import jakarta.mail.MessagingException;
import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;
import org.example.files.classes.tasks.OneTimeTasks;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserInterface {
    DateAndTime dateAndTime;
    TaskManager taskManager;
    MessageSender messageSender;
    UserDataSaving userDataSaving;
    ArrayList<OneTimeTasks> createdTasks;

    public void gettingUserData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Plase enter folder path: ");
        String foldPath = scanner.nextLine(); // "/Users/ahmetyusufyildirim/Desktop";

        System.out.println("Plase enter folder name: ");
        String foldName = scanner.nextLine(); // "TaskPlanner";

        TaskManager.createFolder(foldPath, foldName);

        System.out.println("Please enter file name: ");
        String filName = scanner.nextLine(); // "UserData";

        TaskManager.createFile(foldPath + File.separator + foldName, filName);
        foldPath = foldPath + File.separator + foldName  + File.separator +filName;
        String strTemp;

        System.out.println("Please enter your time zone: ");
        strTemp = scanner.nextLine();
        try {
            dateAndTime.setZoneIdString(strTemp);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format. Please enter a valid format.");
        }
        userDataSaving.writeUserInfo("User time zone: " + strTemp, foldPath);

        System.out.println("Please enter your email address (@gmail.com): ");
        strTemp = scanner.nextLine() + "@gmail.com";
        messageSender.setEmail_To(strTemp);
        userDataSaving.writeUserInfo("User gmail: " + strTemp, foldPath);
    }

    public void loop()  {
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
                    System.out.println("Please enter '1' for empty constructor, and '2' for personal input: ");
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
                    date = dateAndTime.getDate();
                    System.out.println("Today is: " + date);
                    break;
                case "2":
                    time = dateAndTime.getTime(dateAndTime.getPatternHour());
                    System.out.println("The current time is: " + time);
                    break;
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
                    System.out.println("Please enter the path of the folder: ");
                    String path = scanner.nextLine();
                    System.out.println("Please enter the name of the folder: ");
                    name = scanner.nextLine();
                    TaskManager.createFolder(path, name);

                    boolean result = TaskManager.createFolder("/Users/ahmetyusufyildirim/Desktop", "UserLibrary");

                    if (result) {
                        System.out.println("Folder is created successfully");
                    } else {
                        System.out.println("Error Found! Unable to create folder.");
                    }
                    break;
                case "13":
                    System.out.println("Please enter the path of the folder you wish to put the file in: ");
                    path = scanner.nextLine();
                    System.out.println("Please enter the name of the file: ");
                    name = scanner.nextLine();

                    taskManager.createFile(path, name);
                    break;
                case "14":
                    MessageSender messageSender = new MessageSender();
                    System.out.println("Please enter the required information for your task: ");

                    System.out.println("Task name: ");
                    String taskName = scanner.nextLine();

                    System.out.println("Task name: ");
                    String description = scanner.nextLine();

                    System.out.println("EndTime: ");
                    String endTime = scanner.nextLine();

                    System.out.println("End Date: ");
                    String endDate = scanner.nextLine();

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

                    createdTasks.add(new OneTimeTasks(taskName, description, endTime, endDate, levelOfImp, CompStat, temp));

                    System.out.println("Enter title of the message: ");
                    String message = scanner.nextLine();

                    System.out.println("Enter the message you wish to send: ");
                    String subject = scanner.nextLine();
                    try {
                        messageSender.sendEmailAt(message, subject, endDate, endTime);
                    } catch (MessagingException e) {
                        System.out.println("Failed to send email: " + e.getMessage());
                    }
                    break;
                case "15":
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
                case "16":
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
                case "17":
                    ArrayList<String> display = new ArrayList<>();
                    for (OneTimeTasks item : createdTasks) {
                        display.add(String.valueOf(item.getListOfTags()));
                    }
                    break;
                case "18":
                    System.out.print("Enter tag to check: ");
                    String tagToCheck = scanner.nextLine().toUpperCase();

                    for (OneTimeTasks item : createdTasks) {
                        item.tagExists(tagToCheck);
                    }
                    break;
                case "19":
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
                case "20":
                    System.out.print("Enter start time (HH:mm:ss): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (HH:mm:ss): ");
                    endTime = scanner.nextLine();
                    dateAndTime.setTime(startTime, endTime);
                    break;
                case "21":
                    System.out.println("Next ID: " + OneTimeTasks.getNextId());
                    break;
                case "22":
                    System.out.println("Please enter the index of element you wish to get the ID: ");
                    num = scanner.nextInt();
                    System.out.println("Task ID: " + createdTasks.get(num).getTaskId());
                    break;
                case "23":
                    System.out.println("Please enter the index of element you wish to get the ID: ");
                    num = scanner.nextInt();
                    System.out.println("Task Name: " + createdTasks.get(num).getTaskName());
                    break;
                case "24":
                    System.out.println("Enter the index of the task you wish to modify the name: ");
                    num = scanner.nextInt();

                    System.out.print("Enter new task name: ");
                    taskName = scanner.nextLine();
                    createdTasks.get(num).setTaskName(taskName);
                    System.out.println("Task Name: " + taskName);
                    break;
                case "25":
                    System.out.println("Enter the index of the task you wish to modify the name: ");
                    num = scanner.nextInt();
                    System.out.println("Short Description: " + createdTasks.get(num).getShortDescription());
                    break;
                case "26":
                    System.out.println("Enter the index of the task you wish to modify the name: ");
                    num = scanner.nextInt();

                    System.out.print("Enter new short description: ");
                    String shortDescription = scanner.nextLine();
                    createdTasks.get(num).setShortDescription(shortDescription);
                    System.out.println("Short Description entered.");
                    break;

                case "27":
                    System.out.println("Enter the index of the task you wish to get the starting time: ");
                    num = scanner.nextInt();
                    System.out.println("Start Time: " + createdTasks.get(num).getStartTime());
                    break;
                case "28":
                    System.out.println("Enter the index of the task you wish to set the starting time: ");
                    num = scanner.nextInt();
                    System.out.println("Enter new starting time: ");
                    startTime = scanner.nextLine();
                    createdTasks.get(num).setStartTime(startTime);
                    break;
                case "29":
                    System.out.println("Enter the index of the task you wish to get the starting date: ");
                    num = scanner.nextInt();

                    System.out.println("Start Time: " + createdTasks.get(num).getStartDate());
                    break;
                case "30":
                    System.out.println("Enter the index of the task you wish to get the starting time: ");
                    num = scanner.nextInt();

                    System.out.println("Enter new starting date: ");
                    startTime = scanner.nextLine();
                    createdTasks.get(num).setStartDate(startTime);
                    break;
                case "31":
                    System.out.println("Enter the index of the task you wish to get the end time: ");
                    num = scanner.nextInt();

                    System.out.println("End Time: " + createdTasks.get(num).getEndTime());
                    break;
                case "32":
                    System.out.println("Enter the index of the task you wish to set the end time: ");
                    num = scanner.nextInt();

                    System.out.println("Enter new end time: ");
                    endTime = scanner.nextLine();
                    createdTasks.get(num).setEndTime(endTime);
                    break;
                case "33":
                    System.out.println("Enter the index of the task you wish to get the end date: ");
                    num = scanner.nextInt();
                    System.out.println("End Time: " + createdTasks.get(num).getEndDate());
                    break;
                case "34":
                    System.out.println("Enter the index of the task you wish to set the end date: ");
                    num = scanner.nextInt();

                    System.out.println("Enter new end date: ");
                    endDate = scanner.nextLine();
                    createdTasks.get(num).setEndDate(endDate);
                    break;
                case "35":
                    bubbleSortByEndDate(createdTasks);
                    break;
                case "36":
                    insertionSortByImportance(createdTasks);
                    break;
                case "37":
                    System.out.println("Please enter the task name you want to search for: ");
                    String targetName = scanner.nextLine();
                    int resultIndex = binarySearchByName(createdTasks, targetName);
                    if (resultIndex != -1) {
                        OneTimeTasks foundTask = createdTasks.get(resultIndex);
                        System.out.println("Task found: " + foundTask);
                    } else {
                        System.out.println("Task with name '" + targetName + "' not found.");
                    }
                    break;
                case "38":
                    for (Object item : createdTasks) {
                        System.out.println(item);
                    }

                    System.out.println("Give the index of the item you wish to add to a specific file: ");
                    num = scanner.nextInt() - 1;

                    System.out.println("Enter the file name: ");
                    name = scanner.nextLine() + ".txt";

//                    taskManager.addTaskToFile(createdTasks.get(num), name);
                    break;
                case "39":
                    for (Object item : createdTasks) {
                        System.out.println(item);
                    }

                    System.out.println("Give the index of the item you wish to remove from a specific file: ");
                    num = scanner.nextInt() - 1;

                    System.out.println("Enter the file name: ");
                    name = scanner.nextLine() + ".txt";

//                    taskManager.removeTaskToFile(createdTasks.get(num), name);

                break;
                default:
                    System.out.println("Unknown command: " + userInput);
                    break;
            }
            scanner.close();
        }
    }


    public static void bubbleSortByEndDate(ArrayList<OneTimeTasks> createdTasks) {
        int n = createdTasks.size();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                LocalDate date1 = LocalDate.parse(createdTasks.get(j).getEndDate(), formatter);
                LocalDate date2 = LocalDate.parse(createdTasks.get(j + 1).getEndDate(), formatter);
                if (date1.isAfter(date2)) {
                    // Swap tasks[j] and tasks[j+1]
                    OneTimeTasks temp = createdTasks.get(j);
                    createdTasks.set(j, createdTasks.get(j + 1));
                    createdTasks.set(j + 1, temp);
                }
            }
        }
    }

    public static void insertionSortByImportance(ArrayList<OneTimeTasks> createdTasks) {
        Map<String, Integer> importanceMap = new HashMap<>();
        importanceMap.put("CRITICAL", 0);
        importanceMap.put("HIGH_PRIORITY", 1);
        importanceMap.put("MEDIUM_HIGH_PRIORITY", 2);
        importanceMap.put("MEDIUM_PRIORITY", 3);
        importanceMap.put("MEDIUM_LOW_PRIORITY", 4);
        importanceMap.put("LOW_PRIORITY", 5);
        importanceMap.put("OPTIONAL", 6);
        importanceMap.put("SOMEDAY", 7);
        importanceMap.put("TRIVIAL", 8);

        int n = createdTasks.size();
        for (int i = 1; i < n; i++) {
            OneTimeTasks key = createdTasks.get(i);
            int keyImportance = importanceMap.get(key.getLevelOfImportance());
            int j = i - 1;

            // Compare current element with the sorted portion
            while (j >= 0 && importanceMap.get(createdTasks.get(j).getLevelOfImportance()) > keyImportance) {
                createdTasks.set(j + 1, createdTasks.get(j));
                j = j - 1;
            }
            createdTasks.set(j + 1, key);
        }
    }

    public static int binarySearchByName(ArrayList<OneTimeTasks> createdTasks, String targetName) {
        Collections.sort(createdTasks, Comparator.comparing(OneTimeTasks::getTaskName));

        int low = 0;
        int high = createdTasks.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String midName = createdTasks.get(mid).getTaskName();

            if (midName.compareTo(targetName) < 0) {
                low = mid + 1;
            } else if (midName.compareTo(targetName) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

