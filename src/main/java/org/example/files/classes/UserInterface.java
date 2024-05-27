package org.example.files.classes;

import jakarta.mail.MessagingException;
import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;
import org.example.files.classes.tasks.OneTimeTasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.example.files.classes.DateAndTime.*;
import static org.example.files.classes.DateAndTime.isValidTimeFormat;

public class UserInterface {

    MessageSender messageSender;



    /**
     * This method allow OneTimeTasks objects to be classed according to their EndDate.
     * Time complexity O(n^2).
     *
     * @param createdTasks stores all the created tasks.
     */
    public static void bubbleSortByEndDate(ArrayList<OneTimeTasks> createdTasks) {
        int n = createdTasks.size();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                LocalDate date1 = LocalDate.parse(createdTasks.get(j).getEndDate(), formatter);
                LocalDate date2 = LocalDate.parse(createdTasks.get(j + 1).getEndDate(), formatter);
                if (date1.isAfter(date2)) {
                    OneTimeTasks temp = createdTasks.get(j);
                    createdTasks.set(j, createdTasks.get(j + 1));
                    createdTasks.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * This method sort based on the level of importance of the object.
     * Time complexity O(n^2).
     *
     * @param createdTasks contains all the OneTimeTasks objects
     */
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

            while (j >= 0 && importanceMap.get(createdTasks.get(j).getLevelOfImportance()) > keyImportance) {
                createdTasks.set(j + 1, createdTasks.get(j));
                j = j - 1;
            }
            createdTasks.set(j + 1, key);
        }
    }

    /**
     * Performs a binary search to find a task by its name in a sorted list of tasks.
     * Time complexity O(log n).
     *
     * @param createdTasks The list of tasks to search in.
     * @param targetName   The name of the task to search for.
     * @return The index of the task if found; otherwise, returns -1.
     */

    public static int binarySearchByName(ArrayList<OneTimeTasks> createdTasks, String targetName) {
        createdTasks.sort(Comparator.comparing(OneTimeTasks::getTaskName));

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

    /**
     * This method allows user to create a saving file where he can deposit all personal information related to him.
     */
    public void gettingUserData(DateAndTime dateAndTime) {
        Scanner scanner = new Scanner(System.in);

        String strTemp;

        System.out.println("Please enter your time zone: ");
        strTemp = scanner.nextLine();
        try {
            dateAndTime.setZoneIdString(strTemp);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format. Please enter a valid format.");
        }

        System.out.println("Please enter your email address (@gmail.com) without the email domain: ");
        strTemp = scanner.nextLine() + "@gmail.com";
        messageSender.setEmail_To(strTemp);
        scanner.close();
    }

    /**
     * Prompts the user to input the index of a task and validates the input.
     * Displays the given prompt string and repeatedly prompts the user to enter an index until a valid index is entered.
     *
     * @param print        The prompt message to display to the user.
     * @param createdTasks The ArrayList of OneTimeTasks from which the user will select the index.
     * @return The index of the selected task.
     */
    public static int getTaskIndex(String print, ArrayList<OneTimeTasks> createdTasks) {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.println(print);
            number = scanner.nextInt();
            scanner.nextLine();
            if (number < 1 || number > createdTasks.size()) {
                System.out.println("Invalid index. Please enter an index between 1 and " + createdTasks.size() + ".");
            }
        } while (number < 1 || number > createdTasks.size());
        return number;
    }


    /**
     * Prints the list of OneTimeTasks.
     *
     * @param createdTasks The ArrayList of OneTimeTasks to print.
     */
    public void printList(ArrayList<OneTimeTasks> createdTasks) {
        for (Object item : createdTasks) {
            System.out.println(item.toString());
        }
    }

    /**
     * This method allows for the user to interact with the program.
     */
    public void loop()  {
        Scanner scanner = new Scanner(System.in);
        DateAndTime dateAndTime = new DateAndTime();
        TaskManager taskManager = new TaskManager();
        ArrayList<OneTimeTasks> createdTasks = new ArrayList<>();

        String userInput = "";
        String date;
        String time;
        String name;
        String path;
        String taskName;
        String endTime;
        String endDate;
        String startTime;
        String file;
        int num;
        int index;
        boolean result;

        while (!userInput.equalsIgnoreCase("STOP")) {
            System.out.println();

            System.out.println("Please enter the number of action you wish to perform: ");
            System.out.println("""
                    1. Date and Time operations
                    2. Task operations
                    3. File and Folder operations
                    4. Other operations
                    """);
            userInput = scanner.nextLine().toUpperCase();
            switch (userInput) {
                case "1":
                    System.out.println("Please enter the number of action you wish to perform: ");
                    System.out.println("""
                            1. Display today's date.
                            2. Display the current time.
                            3. Check if a date is valid.
                            4. Check if a date is valid today.
                            5. Set the zone according to a continent and city or change the city.
                            6. Display the current zone.
                            7. Compare a given date and time with the current date and time.
                            8. Display the current day pattern.
                            9. Display the current time pattern.
                            10. Set a new date pattern.
                            11. Set a new time pattern.
                            """);
                    userInput = scanner.nextLine();
                    switch (userInput) {
                        case "1":
                            System.out.println("Today is: " + dateAndTime.getDate(dateAndTime.getZoneIdString()));
                            break;
                        case "2":
                            System.out.println("The current time is: " + dateAndTime.getTime());
                            break;
                        case "3":
                            do {
                                System.out.println("Please enter the date in a valid format (dd-MM-yyyy): ");
                                date = scanner.nextLine();
                            } while (!isValidDateFormat(date) && !dateNotNull(date));

                            System.out.println("The entered date is: " + dateAndTime.dateExists(date));
                            break;
                        case "4":
                            do {
                                System.out.println("Enter a date in a valid format (dd-MM-yyyy) to check if it has already past or has yet to come: ");
                                date = scanner.nextLine();
                            } while (!isValidDateFormat(date) && !dateNotNull(date));
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
                            System.out.println("The current zone is: " + dateAndTime.getZoneIdString());
                            break;
                        case "7":
                            do {
                                System.out.println("Please enter the end date (dd-MM-yyyy): ");
                                date = scanner.nextLine();
                            } while (!isValidDateFormat(date) && !dateNotNull(date) && !dateAndTime.dateValidToday(date));

                            do {
                                System.out.println("Please enter the end time (HH:mm:ss): ");
                                time = scanner.nextLine();
                            } while (!isValidTimeFormat(time) && !isValidTimeValues(time));
                            System.out.println(dateAndTime.compareDateTime(date, time));
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
                                System.out.println("Your new time pattern has been set.");
                                break;
                        default:
                            System.out.println("Unknown command: " + userInput);
                            break;
                    }
                    break;
                case "2":
                    System.out.println("Please enter the number of action you wish to perform: ");
                    System.out.println("""
                            1. Sort created tasks by dates and times.
                            2. Create a new task and send a message.
                            3. Add a tag to a task.
                            4. Remove a tag from a task.
                            5. Display tags of all tasks.
                            6. Check if a tag exists in any task.
                            7. Set a new date for a task.
                            8. Set start and end times.
                            9. Display the next task ID.
                            10. Display the task ID at a specific index.
                            11. Display the task name at a specific index.
                            12. Modify the name of a task.
                            13. Display the short description of a task.
                            14. Modify the short description of a task.
                            15. Display the start time of a task.
                            16. Set the start time of a task.
                            17. Display the start date of a task.
                            18. Set the start date of a task.
                            19. Display the end time of a task.
                            20. Set the end time of a task.
                            21. Display the end date of a task.
                            22. Set the end date of a task.                                               
                            23. Add a task to a specific file.                
                            24. Remove a task from a specific file.
                            25. Change the level of importance of a task.
                            26. Change the completion status of a task.
                            27. Display all tasks.
                            """);
                    userInput = scanner.nextLine();
                    switch (userInput) {
                        case "1":
                            DateComparator dateComparator = new DateComparator();
                            TimeComparator timeComparator = new TimeComparator();

                            createdTasks.sort(dateComparator);

                            System.out.println("The created tasks arranged according to their dates: ");
                            for (OneTimeTasks task : createdTasks) {
                                System.out.println(task);
                            }

                            createdTasks.sort(timeComparator);

                            System.out.println("The created tasks arranged according to their times: ");
                            for (OneTimeTasks task : createdTasks) {
                                System.out.println(task);
                            }
                            break;
                        case "2":
                            MessageSender messageSender = new MessageSender();
                            System.out.println("Please enter the required information for your task: ");

                            System.out.println("Task name: ");
                            taskName = scanner.nextLine();

                            System.out.println("Description: ");
                            String description = scanner.nextLine();

                            do {
                                System.out.println("End Time: ");
                                endTime = scanner.nextLine();
                            } while (!isValidTimeFormat(endTime) && !isValidTimeValues(endTime));

                            do {
                                System.out.println("End Date: ");
                                endDate = scanner.nextLine();
                            } while (!isValidDateFormat(endDate) && !dateNotNull(endDate) && !dateAndTime.dateValidToday(endDate));


                            System.out.println("Enter the number corresponding to the level of importance:");
                            for (int i = 0; i < LevelOfImportance.values().length; i++) {
                                System.out.println((i + 1) + ". " + LevelOfImportance.values()[i]);
                            }

                            System.out.print("Enter your choice: ");
                            num = scanner.nextInt();

                            String levelOfImp = (num >= 1 && num <= LevelOfImportance.values().length) ? LevelOfImportance.values()[num - 1].toString() : "Unknown";

                            System.out.println("Enter the number corresponding to the completion status:");
                            for (int i = 0; i < CompletionStatus.values().length; i++) {
                                System.out.println((i + 1) + ". " + CompletionStatus.values()[i]);
                            }

                            System.out.print("Enter your choice: ");
                            num = scanner.nextInt();

                            String CompStat = (num >= 1 && num <= CompletionStatus.values().length) ? CompletionStatus.values()[num - 1].toString() : "Unknown";

                            System.out.println("Enter the number of tags: ");
                            num = scanner.nextInt();

                            scanner.nextLine();

                            ArrayList<String> temp = new ArrayList<>();
                            for (int i = 0; i < num; i++) {
                                System.out.println("Enter the tag(s): ");
                                String tag = scanner.nextLine().toUpperCase();
                                temp.add(tag);
                            }

                            createdTasks.add(new OneTimeTasks(taskName, description, endTime, endDate, levelOfImp, CompStat, temp, dateAndTime));

//                            System.out.println("Enter title of the message: ");
//                            String message = scanner.nextLine();
//
//                            do {
//                                System.out.println("Please enter the date that you want to receive the notification: ");
//                                endDate = scanner.nextLine();
//                            } while (!isValidDateFormat(endDate) && !dateNotNull(endDate) && !dateAndTime.dateValidToday(endDate));
//
//
//                            do {
//                                System.out.println("Please enter the time you wish to be notified: ");
//                                endTime = scanner.nextLine();
//                            } while (!isValidTimeFormat(endTime) && !isValidTimeValues(endTime));
//
//                            System.out.println("Enter the message you wish to send: ");
//                            String subject = scanner.nextLine();
//                            try {
//                                messageSender.sendEmailAt(message, subject, endDate, endTime);
//                            } catch (MessagingException e) {
//                                System.out.println("Failed to send email: " + e.getMessage());
//                            }
                            break;
                        case "3":
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
                        case "4":
                            System.out.print("Enter tag to remove: ");
                            String tagToRemove = scanner.nextLine().toUpperCase();

                            for (OneTimeTasks item : createdTasks) {
                                System.out.println(item);
                            }

                            System.out.println("Please give the ID of task you wish to remove: ");
                            name = scanner.nextLine();

                            boolean tagRemoved = false;

                            for (OneTimeTasks item : createdTasks) {
                                System.out.println(item.getTaskId());
                                System.out.println(name);
                                if (item.getTaskId().equals(name)) {
                                    if (item.getListOfTags().remove(tagToRemove)) {
                                        System.out.println("Tag removed successfully.");
                                        tagRemoved = true;
                                        break;
                                    } else {
                                        System.out.println("The entered tag could not be found.");
                                        tagRemoved = true; // Set flag to true
                                        break;
                                    }
                                }
                            }
                            if (!tagRemoved) {
                                System.out.println("The entered task ID does not exist.");
                            }
                            break;
                        case "5":
                            ArrayList<String> display = new ArrayList<>();
                            for (OneTimeTasks item : createdTasks) {
                                display.add(String.valueOf(item.getListOfTags()));
                            }

                            System.out.println("Tags of all tasks: ");
                            for (String tags : display) {
                                System.out.println(tags);
                            }
                            break;
                        case "6":
                            System.out.print("Enter tag to check: ");
                            String tagToCheck = scanner.nextLine().toUpperCase();

                            for (OneTimeTasks item : createdTasks) {
                                item.tagExists(tagToCheck);
                            }
                            break;
                        case "7":
                            String dateToSet;
                            do {
                                System.out.print("Enter date to set: ");
                                dateToSet = scanner.nextLine();
                            } while (!isValidDateFormat(dateToSet) && !dateNotNull(dateToSet) && !dateAndTime.dateValidToday(dateToSet));

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
                        case "8":
                            do {
                                System.out.println("Please enter the end time (HH:mm:ss): ");
                                endTime = scanner.nextLine();
                            } while (!isValidTimeFormat(endTime) && !isValidTimeValues(endTime));

                            do {
                                System.out.print("Enter start time (HH:mm:ss): ");
                                startTime = scanner.nextLine();
                            } while (!isValidTimeFormat(startTime) && !isValidTimeValues(startTime));

                            scanner.nextLine();
                            dateAndTime.setTime(startTime, endTime);
                            break;
                        case "9":
                            System.out.println("Next ID: " + OneTimeTasks.getNextId());
                            break;
                        case "10":
                            printList(createdTasks);
                            index = getTaskIndex("Please enter the index of element you wish to get the ID (1-based index): ", createdTasks);
                            System.out.println("Task ID: " + createdTasks.get(index - 1).getTaskId());
                            break;
                        case "11":
                            printList(createdTasks);
                            num = getTaskIndex("Please enter the index of element you wish to get the ID (1-based index): ", createdTasks);
                            System.out.println("Task Name: " + createdTasks.get(num - 1).getTaskName());
                            break;
                        case "12":
                            printList(createdTasks);
                            num = getTaskIndex("Enter the index of the task you wish to modify the name (1-based index): ", createdTasks);

                            System.out.print("Enter new task name: ");
                            taskName = scanner.nextLine();

                            createdTasks.get(num - 1).setTaskName(taskName);
                            System.out.println("Task Name: " + taskName);
                            break;
                        case "13":
                            printList(createdTasks);
                            num = getTaskIndex("Enter the index of the task you wish to review the description (1-based index): ", createdTasks);
                            System.out.println("Short Description: " + createdTasks.get(num - 1).getShortDescription());
                            break;
                        case "14":
                            printList(createdTasks);

                            num = getTaskIndex("Enter the index of the task you wish to modify the description (1-based index): ", createdTasks);

                            System.out.print("Enter new short description: ");
                            String shortDescription = scanner.nextLine();
                            createdTasks.get(num -1).setShortDescription(shortDescription);
                            break;
                        case "15":
                            printList(createdTasks);

                            num = getTaskIndex("Enter the index of the task you wish to get the starting time (1-based index): ", createdTasks);
                            System.out.println("Start Time: " + createdTasks.get(num - 1).getStartTime());
                            break;
                        case "16":
                            printList(createdTasks);

                            num = getTaskIndex("Enter the index of the task you wish to get the starting time (1-based index): ", createdTasks);

                            do {
                                System.out.println("Enter new starting time: ");
                                startTime = scanner.nextLine();
                            } while (!isValidTimeFormat(startTime) && !isValidTimeValues(startTime));

                            createdTasks.get(num -1).setStartTime(startTime);
                            break;
                        case "17":
                            printList(createdTasks);

                            num = getTaskIndex("Enter the index of the task you wish to get the starting time (1-based index): ", createdTasks);
                            System.out.println("Start Time: " + createdTasks.get(num - 1).getStartDate());
                            break;
                        case "18":
                            printList(createdTasks);

                            num = getTaskIndex("Enter the index of the task you wish to set the starting date (1-based index): ", createdTasks);

                            do {
                                System.out.println("Enter new starting date: ");
                                date = scanner.nextLine();
                            } while (!isValidDateFormat(date) && !dateNotNull(date));
                            createdTasks.get(num - 1).setStartDate(date);
                            break;
                        case "19":
                            printList(createdTasks);

                            num = getTaskIndex("Enter the index of the task you wish to get the end time (1-based index): ", createdTasks);
                            System.out.println("End Time: " + createdTasks.get(num - 1).getEndTime());
                            break;
                        case "20":
                            printList(createdTasks);

                            num = getTaskIndex("Enter the index of the task you wish to set the end time (1-based index): ", createdTasks);


                            do {
                                System.out.println("Enter new end time: ");
                                endTime = scanner.nextLine();
                            } while (!isValidTimeFormat(endTime) && !isValidTimeValues(endTime));

                            createdTasks.get(num - 1).setEndTime(endTime);
                            break;
                        case "21":
                            printList(createdTasks);

                            num = getTaskIndex("Enter the index of the task you wish to get the end date (1-based index): ", createdTasks);
                            System.out.println("End Time: " + createdTasks.get(num - 1).getEndDate());
                            break;
                        case "22":
                            printList(createdTasks);

                            num = getTaskIndex("Enter the index of the task you wish to set the end date (1-based index): ", createdTasks);
                            do {
                                System.out.println("Enter new end date: ");
                                endDate = scanner.nextLine();
                            } while (!isValidDateFormat(endDate) && !dateNotNull(endDate));
                            createdTasks.get(num - 1).setEndDate(endDate);
                            break;
                        case "23":

                            printList(createdTasks);
                            num = getTaskIndex("Give the index of the item you wish to add to a specific file (1-based index): ", createdTasks);

                            System.out.println("Enter the file path: ");
                            name = scanner.nextLine();

                            System.out.println("Enter the file name: ");
                            file = scanner.nextLine();

                            taskManager.addTaskToFile(createdTasks.get(num - 1), name, file);
                            break;
                        case "24":

                            printList(createdTasks);
                            index = getTaskIndex("Give the index of the item you wish to remove from a specific file (1-based index): ", createdTasks);

                            System.out.println("Enter the file path: ");
                            name = scanner.nextLine();

                            System.out.println("Enter the file name: ");
                            file = scanner.nextLine() + ".txt";

                            taskManager.removeTaskToFile(createdTasks, createdTasks.get(index - 1), name, file);
                            break;
                        case "25":
                            printList(createdTasks);
                            index = getTaskIndex("Enter the index of the task you wish to modify the level of importance (1-based index): ", createdTasks);

                            if (index >= 0 && index < createdTasks.size()) {
                                System.out.println("Enter the number corresponding to the new level of importance: ");
                                for (int i = 0; i < LevelOfImportance.values().length; i++) {
                                    System.out.println((i + 1) + ". " + LevelOfImportance.values()[i]);
                                }

                                System.out.print("Enter your choice: ");
                                int choice = scanner.nextInt();

                                if (choice >= 1 && choice <= LevelOfImportance.values().length) {
                                    createdTasks.get(index).setLevelOfImportance(String.valueOf(LevelOfImportance.values()[choice - 1]));
                                    System.out.println("Level of importance updated successfully.");
                                } else {
                                    System.out.println("Invalid choice.");
                                }
                            } else {
                                System.out.println("Invalid index.");
                            }
                            break;
                        case "26":
                            printList(createdTasks);
                            index = getTaskIndex("Enter the index of the task you wish to modify the level of importance (1-based index): ", createdTasks);

                            if (index >= 0 && index < createdTasks.size()) {
                                System.out.println("Enter the number corresponding to the new completion status: ");
                                for (int i = 0; i < CompletionStatus.values().length; i++) {
                                    System.out.println((i + 1) + ". " + CompletionStatus.values()[i]);
                                }

                                System.out.print("Enter your choice: ");
                                int choice = scanner.nextInt();

                                if (choice >= 1 && choice <= LevelOfImportance.values().length) {
                                    createdTasks.get(index).setCompletionStatus(String.valueOf(LevelOfImportance.values()[choice - 1]));
                                    System.out.println("Level of importance updated successfully.");
                                } else {
                                    System.out.println("Invalid choice.");
                                }
                            } else {
                                System.out.println("Invalid index.");
                            }
                            break;
                        case "27":
                            printList(createdTasks);
                            break;
                        default:
                            System.out.println("Unknown command: " + userInput);
                            break;
                    }
                    break;
                case "3":
                    System.out.println("Please enter the number of action you wish to perform: ");
                    System.out.println("""
                            1. Create a new folder.
                            2. Create a new file.
                            """);
                    userInput = scanner.nextLine();
                    switch (userInput) {
                        case "1":
                            do {
                                System.out.println("Please enter the path of the folder: ");
                                path = scanner.nextLine();
                                System.out.println("Please enter the name of the folder: ");
                                name = scanner.nextLine();
                                result = TaskManager.createFolder(path, name);
                                if (result) {
                                    System.out.println("Folder is created successfully");
                                } else {
                                    System.out.println("Error Found! Unable to create folder.");
                                }
                            } while (!result);
                            break;
                        case "2":
                            do {
                                System.out.println("Please enter the path of the folder you wish to put the file in: ");
                                path = scanner.nextLine();
                                System.out.println("Please enter the name of the file: ");
                                name = scanner.nextLine();

                                result = TaskManager.createFile(path, name);
                            } while (result);
                            break;
                        default:
                            System.out.println("Unknown command: " + userInput);
                            break;
                    }
                    break;
                case "4":
                    System.out.println("Please enter the number of action you wish to perform: ");
                    System.out.println("""
                            1. Sort tasks by end date using bubble sort.
                            2. Sort tasks by importance using insertion sort.
                            3. Search for a task by name using binary search.
                            4. To send an email.
                            """);
                    userInput = scanner.nextLine();
                    switch (userInput) {
                        case "1":
                            bubbleSortByEndDate(createdTasks);
                            break;
                        case "2":
                            insertionSortByImportance(createdTasks);
                            break;
                        case "3":
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
                        case "4":
                            System.out.println("Enter title of the message: ");
                            String message = scanner.nextLine();

                            do {
                                System.out.println("Please enter the date that you want to receive the notification: ");
                                endDate = scanner.nextLine();
                            } while (!isValidDateFormat(endDate) && !dateNotNull(endDate));


                            do {
                                System.out.println("Please enter the time you wish to be notified: ");
                                endTime = scanner.nextLine();
                            } while (!isValidTimeFormat(endTime) && !isValidTimeValues(endTime));

                            System.out.println("Enter the message you wish to send: ");
                            String subject = scanner.nextLine();
                            try {
                                messageSender.sendEmailAt(message, subject, endDate, endTime);
                            } catch (MessagingException e) {
                                System.out.println("Failed to send email: " + e.getMessage());
                            }
                        default:
                            System.out.println("Unknown command: " + userInput);
                            break;
                    }
                default:
                    System.out.println("Unknown command: " + userInput);
                    break;
            }
        }
    }
}




