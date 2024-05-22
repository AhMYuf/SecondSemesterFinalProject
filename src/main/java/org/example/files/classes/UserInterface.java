package org.example.files.classes;

import jakarta.mail.MessagingException;
import org.example.files.classes.reference.CompletionStatus;
import org.example.files.classes.reference.LevelOfImportance;
import org.example.files.classes.tasks.DateComparator;
import org.example.files.classes.tasks.OneTimeTasks;
import org.example.files.classes.tasks.TimeComparator;

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

    /**
     * This method allow OneTimeTasks objects to be classed according to their
     *
     * @param createdTasks
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

            // Compare current element with the sorted portion
            while (j >= 0 && importanceMap.get(createdTasks.get(j).getLevelOfImportance()) > keyImportance) {
                createdTasks.set(j + 1, createdTasks.get(j));
                j = j - 1;
            }
            createdTasks.set(j + 1, key);
        }
    }

    /**
     * Performs a binary search to find a task by its name in a sorted list of tasks.
     *
     * @param createdTasks The list of tasks to search in.
     * @param targetName   The name of the task to search for.
     * @return The index of the task if found; otherwise, returns -1.
     */

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

    /**
     * This method allows user to create a saving file where he can deposit all personal information related to him.
     */
    public void gettingUserData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter folder path: ");
        String foldPath = scanner.nextLine(); // "/Users/ahmetyusufyildirim/Desktop";

        System.out.println("Please enter folder name: ");
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

    /**
     * This method allows for the user to interact with the program.
     */
    public void loop()  {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String date;
        String time;
        String name;

        while (!userInput.equals("STOP")) {
            System.out.println("Please enter the number of action you wish to perform: ");
            System.out.println(
                    "0. Create a new DateAndTime object.\n" +
                    "1. Display today's date.\n" +
                    "2. Display the current time.\n" +
                    "3. Check if a date is valid.\n" +
                    "4. Check if a date is valid today.\n" +
                    "5. Set the zone according to a continent and city or change the city.\n" +
                    "6. Display the current zone.\n" +
                    "7. Compare a given date and time with the current date and time.\n" +
                    "8. Display the current day pattern.\n" +
                    "9. Display the current time pattern.\n" +
                    "10. Set a new date pattern.\n" +
                    "11. Set a new time pattern.\n" +
                    "12. Sort created tasks by dates and times.\n" +
                    "13. Create a new folder.\n" +
                    "14. Create a new file.\n" +
                    "15. Create a new task and send a message.\n" +
                    "16. Add a tag to a task.\n" +
                    "17. Remove a tag from a task.\n" +
                    "18. Display tags of all tasks.\n" +
                    "19. Check if a tag exists in any task.\n" +
                    "20. Set a new date for a task.\n" +
                    "21. Set start and end times.\n" +
                    "22. Display the next task ID.\n" +
                    "23. Display the task ID at a specific index.\n" +
                    "24. Display the task name at a specific index.\n" +
                    "25. Modify the name of a task.\n" +
                    "26. Display the short description of a task.\n" +
                    "27. Modify the short description of a task.\n" +
                    "28. Display the start time of a task.\n" +
                    "29. Set the start time of a task.\n" +
                    "30. Display the start date of a task.\n" +
                    "31. Set the start date of a task.\n" +
                    "32. Display the end time of a task.\n" +
                    "33. Set the end time of a task.\n" +
                    "34. Display the end date of a task.\n" +
                    "35. Set the end date of a task.\n" +
                    "36. Sort tasks by end date using bubble sort.\n" +
                    "37. Sort tasks by importance using insertion sort.\n" +
                    "38. Search for a task by name using binary search.\n" +
                    "39. Add a task to a specific file.\n" +
                    "40. Remove a task from a specific file.");
            userInput = scanner.nextLine();
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
                    System.out.println("Please enter the date in a valid format (dd-MM-yyyy): ");
                    date = scanner.nextLine();
                    System.out.println("The entered date is: " + dateAndTime.dateExists(date));
                    break;
                case "4":
                    System.out.println("Enter a date in a valid format (dd-MM-yyyy) to check if it has already past or has yet to come: ");
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
                    System.out.println("The current zone is: " + dateAndTime.getZoneIdString());
                    break;
                case "7":
                    System.out.println("Please enter the end date: ");
                    date = scanner.nextLine();
                    System.out.println("Please enter the end time: ");
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
                    DateComparator dateComparator = new DateComparator();
                    TimeComparator timeComparator = new TimeComparator();

                    Collections.sort(createdTasks, dateComparator);

                    System.out.println("The created tasks arranged according to their dates: ");
                    for (OneTimeTasks task : createdTasks) {
                        System.out.println(task);
                    }

                    Collections.sort(createdTasks, timeComparator);

                    System.out.println("The created tasks arranged according to their times: ");
                    for (OneTimeTasks task : createdTasks) {
                        System.out.println(task);
                    }
                    break;
                case "13":
                    System.out.println("Please enter the path of the folder: ");
                    String path = scanner.nextLine(); // /Users/ahmetyusufyildirim/Desktop/TaskPlanner
                    System.out.println("Please enter the name of the folder: ");
                    name = scanner.nextLine(); //
                    TaskManager.createFolder(path, name);

                    boolean result = TaskManager.createFolder(path, name);

                    if (result) {
                        System.out.println("Folder is created successfully");
                    } else {
                        System.out.println("Error Found! Unable to create folder.");
                    }
                    break;
                case "14":
                    System.out.println("Please enter the path of the folder you wish to put the file in: ");
                    path = scanner.nextLine();
                    System.out.println("Please enter the name of the file: ");
                    name = scanner.nextLine();

                    taskManager.createFile(path, name);
                    break;
                case "15":
                    MessageSender messageSender = new MessageSender();
                    System.out.println("Please enter the required information for your task: ");

                    System.out.println("Task name: ");
                    String taskName = scanner.nextLine();

                    System.out.println("Description: ");
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

                    System.out.println("Enter the number corresponding to the completion status:");
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
                case "16":
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
                case "17":
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
                case "18":
                    ArrayList<String> display = new ArrayList<>();
                    for (OneTimeTasks item : createdTasks) {
                        display.add(String.valueOf(item.getListOfTags()));
                    }

                    System.out.println("Tags of all tasks:");
                    for (String tags : display) {
                        System.out.println(tags);
                    }
                    break;
                case "19":
                    System.out.print("Enter tag to check: ");
                    String tagToCheck = scanner.nextLine().toUpperCase();

                    for (OneTimeTasks item : createdTasks) {
                        item.tagExists(tagToCheck);
                    }
                    break;
                case "20":
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
                case "21":
                    System.out.print("Enter start time (HH:mm:ss): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (HH:mm:ss): ");
                    endTime = scanner.nextLine();
                    dateAndTime.setTime(startTime, endTime);
                    break;
                case "22":
                    System.out.println("Next ID: " + OneTimeTasks.getNextId());
                    break;
                case "23":
                    System.out.println("Please enter the index of element you wish to get the ID: ");
                    num = scanner.nextInt();
                    System.out.println("Task ID: " + createdTasks.get(num).getTaskId());
                    break;
                case "24":
                    System.out.println("Please enter the index of element you wish to get the ID: ");
                    num = scanner.nextInt();
                    System.out.println("Task Name: " + createdTasks.get(num).getTaskName());
                    break;
                case "25":
                    System.out.println("Enter the index of the task you wish to modify the name: ");
                    num = scanner.nextInt();

                    System.out.print("Enter new task name: ");
                    taskName = scanner.nextLine();
                    createdTasks.get(num).setTaskName(taskName);
                    System.out.println("Task Name: " + taskName);
                    break;
                case "26":
                    System.out.println("Enter the index of the task you wish to modify the name: ");
                    num = scanner.nextInt();
                    System.out.println("Short Description: " + createdTasks.get(num).getShortDescription());
                    break;
                case "27":
                    System.out.println("Enter the index of the task you wish to modify the name: ");
                    num = scanner.nextInt();

                    System.out.print("Enter new short description: ");
                    String shortDescription = scanner.nextLine();
                    createdTasks.get(num).setShortDescription(shortDescription);
                    System.out.println("Short Description entered.");
                    break;

                case "28":
                    System.out.println("Enter the index of the task you wish to get the starting time: ");
                    num = scanner.nextInt();
                    System.out.println("Start Time: " + createdTasks.get(num).getStartTime());
                    break;
                case "29":
                    System.out.println("Enter the index of the task you wish to set the starting time: ");
                    num = scanner.nextInt();
                    System.out.println("Enter new starting time: ");
                    startTime = scanner.nextLine();
                    createdTasks.get(num).setStartTime(startTime);
                    break;
                case "30":
                    System.out.println("Enter the index of the task you wish to get the starting date: ");
                    num = scanner.nextInt();

                    System.out.println("Start Time: " + createdTasks.get(num).getStartDate());
                    break;
                case "31":
                    System.out.println("Enter the index of the task you wish to get the starting time: ");
                    num = scanner.nextInt();

                    System.out.println("Enter new starting date: ");
                    startTime = scanner.nextLine();
                    createdTasks.get(num).setStartDate(startTime);
                    break;
                case "32":
                    System.out.println("Enter the index of the task you wish to get the end time: ");
                    num = scanner.nextInt();

                    System.out.println("End Time: " + createdTasks.get(num).getEndTime());
                    break;
                case "33":
                    System.out.println("Enter the index of the task you wish to set the end time: ");
                    num = scanner.nextInt();

                    System.out.println("Enter new end time: ");
                    endTime = scanner.nextLine();
                    createdTasks.get(num).setEndTime(endTime);
                    break;
                case "34":
                    System.out.println("Enter the index of the task you wish to get the end date: ");
                    num = scanner.nextInt();
                    System.out.println("End Time: " + createdTasks.get(num).getEndDate());
                    break;
                case "35":
                    System.out.println("Enter the index of the task you wish to set the end date: ");
                    num = scanner.nextInt();

                    System.out.println("Enter new end date: ");
                    endDate = scanner.nextLine();
                    createdTasks.get(num).setEndDate(endDate);
                    break;
                case "36":
                    bubbleSortByEndDate(createdTasks);
                    break;
                case "37":
                    insertionSortByImportance(createdTasks);
                    break;
                case "38":
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
                case "39":
                    for (Object item : createdTasks) {
                        System.out.println(item);
                    }

                    System.out.println("Give the index of the item you wish to add to a specific file: ");
                    num = scanner.nextInt() - 1;

                    System.out.println("Enter the file path: ");
                    name = scanner.nextLine();

                    System.out.println("Enter the file name: ");
                    String file = scanner.nextLine() + ".txt";

                  taskManager.addTaskToFile(createdTasks.get(num), name, file);
                    break;
                case "40":
                    for (Object item : createdTasks) {
                        System.out.println(item);
                    }

                    System.out.println("Give the index of the item you wish to remove from a specific file: ");
                    num = scanner.nextInt() - 1;

                    System.out.println("Enter the file name: ");
                    name = scanner.nextLine();

                    System.out.println("Enter the file name: ");
                    file = scanner.nextLine() + ".txt";

                   taskManager.removeTaskToFile(createdTasks.get(num), name,file);
                case "41":
                    System.out.println("Enter the index of the task you wish to modify the level of importance: ");
                    int index = scanner.nextInt();

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
                case "42":
                    System.out.println("Enter the index of the task you wish to modify the level of importance: ");
                    index = scanner.nextInt();

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
                default:
                    System.out.println("Unknown command: " + userInput);
                    break;
            }
            scanner.close();
        }
    }
}

