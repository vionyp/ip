[33mcommit 04598690b9828d21bb4d1d25d127f113992b1ad2[m[33m ([m[1;36mHEAD[m[33m -> [m[1;32mbranch-Level-5[m[33m, [m[1;31morigin/branch-Level-5[m[33m)[m
Author: vionyp <VIONY001@e.ntu.edu.sg>
Date:   Sun Feb 8 02:24:22 2026 +0800

    Level-5: Handle Errors

[1mdiff --git a/src/main/java/Nivi.class b/src/main/java/Nivi.class[m
[1mindex e2a3965..e098eb0 100644[m
Binary files a/src/main/java/Nivi.class and b/src/main/java/Nivi.class differ
[1mdiff --git a/src/main/java/Nivi.java b/src/main/java/Nivi.java[m
[1mindex 422f95e..e6b0353 100644[m
[1m--- a/src/main/java/Nivi.java[m
[1m+++ b/src/main/java/Nivi.java[m
[36m@@ -1,175 +1,227 @@[m
[31m-[m
 import java.util.Scanner;[m
 [m
 public class Nivi {[m
[31m-    // simplify the code that have repetitions like the line divider[m
[31m-    private static final int MAX_TASKS = 100;[m
[31m-    private static final String DIVIDER = "____________________________________________________________";[m
[32m+[m[32m    private static final int maximumTaskCapacity = 100;[m
[32m+[m[32m    private static final String lineDivider = "____________________________________________________________";[m
 [m
     public static void main(String[] args) {[m
[31m-        Task[] tasks = new Task[MAX_TASKS];[m
[31m-        int taskCount = 0;[m
[32m+[m[32m        Task[] taskList = new Task[maximumTaskCapacity];[m
[32m+[m[32m        int totalTaskCount = 0;[m
 [m
[31m-        String logo = " _   _  _____ __      __ _____\n"[m
[32m+[m[32m        String niviLogo = " _   _  _____ __      __ _____\n"[m
                 + "| \\ | ||_   _|\\ \\    / /|_   _|\n"[m
                 + "|  \\| |  | |   \\ \\  / /   | |  \n"[m
                 + "| . ` |  | |    \\ \\/ /    | |  \n"[m
                 + "| |\\  | _| |_    \\  /    _| |_ \n"[m
                 + "|_| \\_||_____|    \\/    |_____|\n";[m
 [m
[31m-        System.out.println("Hello from\n" + logo);[m
[32m+[m[32m        System.out.println("Hello from\n" + niviLogo);[m
         printDivider();[m
         System.out.println(" Hello! I'm NIVI");[m
         System.out.println(" What can I do for you?");[m
         printDivider();[m
 [m
[31m-        Scanner inStr = new Scanner(System.in);[m
[32m+[m[32m        Scanner userInputScanner = new Scanner(System.in);[m
         while (true) {[m
[31m-            String word = inStr.nextLine();[m
[31m-            String lowerWord = word.toLowerCase();[m
[31m-[m
[31m-            // structuring the code logically[m
[31m-            // give a space and enter to differentiate each if else case[m
[31m-            if (lowerWord.contains("bye")) {[m
[32m+[m[32m            try {[m
[32m+[m[32m                String userInput = userInputScanner.nextLine();[m
[32m+[m[32m                String lowercaseUserInput = userInput.toLowerCase();[m
[32m+[m
[32m+[m[32m                if (lowercaseUserInput.contains("bye")) {[m
[32m+[m[32m                    printDivider();[m
[32m+[m[32m                    System.out.println(" Bye. See you soon little kid! Love u");[m
[32m+[m[32m                    printDivider();[m
[32m+[m[32m                    break;[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m                if (lowercaseUserInput.equals("list")) {[m
[32m+[m[32m                    handleListCommand(taskList, totalTaskCount);[m
[32m+[m[32m                    continue;[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m                if (lowercaseUserInput.startsWith("mark ")) {[m
[32m+[m[32m                    handleMarkCommand(taskList, totalTaskCount, lowercaseUserInput);[m
[32m+[m[32m                    continue;[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m                if (lowercaseUserInput.startsWith("unmark ")) {[m
[32m+[m[32m                    handleUnmarkCommand(taskList, totalTaskCount, lowercaseUserInput);[m
[32m+[m[32m                    continue;[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m                if (lowercaseUserInput.startsWith("todo") || lowercaseUserInput.startsWith("deadline")[m[41m [m
[32m+[m[32m                        || lowercaseUserInput.startsWith("event")) {[m
[32m+[m[32m                    totalTaskCount = handleAddTask(taskList, totalTaskCount, userInput, lowercaseUserInput);[m
[32m+[m[32m                    continue;[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m                throw new NiviException("Please give a specific command: todo/deadline/event/list/mark/unmark/bye");[m
[32m+[m
[32m+[m[32m            } catch (NiviException niviError) {[m
                 printDivider();[m
[31m-                System.out.println(" Bye. See you soon little kid! Love u");[m
[32m+[m[32m                System.out.println(" " + niviError.getMessage());[m
[32m+[m[32m                printDivider();[m
[32m+[m[32m            } catch (Exception unexpectedError) {[m
[32m+[m[32m                printDivider();[m
[32m+[m[32m                System.out.println(" Error: " + unexpectedError.getMessage());[m
                 printDivider();[m
[31m-                break;[m
[31m-            }[m
[31m-[m
[31m-            if (lowerWord.equals("list")) {[m
[31m-                taskCount = handleListCommand(tasks, taskCount);[m
[31m-                continue;[m
[31m-            }[m
[31m-[m
[31m-            if (lowerWord.startsWith("mark ")) {[m
[31m-                handleMarkCommand(tasks, taskCount, lowerWord);[m
[31m-                continue;[m
[31m-            }[m
[31m-[m
[31m-            if (lowerWord.startsWith("unmark ")) {[m
[31m-                handleUnmarkCommand(tasks, taskCount, lowerWord);[m
[31m-                continue;[m
[31m-            }[m
[31m-[m
[31m-            if (lowerWord.startsWith("todo") || lowerWord.startsWith("deadline") || lowerWord.startsWith("event")) {[m
[31m-                taskCount = handleAddTask(tasks, taskCount, word, lowerWord);[m
[31m-                continue;[m
             }[m
[31m-[m
[31m-            printDivider();[m
[31m-            System.out.println(" Please give a specific command: todo/deadline/event/list/mark/unmark/bye");[m
[31m-            printDivider();[m
         }[m
[31m-        inStr.close();[m
[32m+[m[32m        userInputScanner.close();[m
     }[m
 [m
[31m-    // avoid long methods and deep nesting because of the if else[m
[31m-    // so every if else will call another function below[m
[31m-    private static int handleListCommand(Task[] tasks, int taskCount) {[m
[31m-        // IMPROVED: Early return for edge case (W4.6l - happy path prominent)[m
[31m-        if (taskCount == 0) {[m
[31m-            System.out.println("The list is still empty");[m
[31m-            return taskCount;[m
[32m+[m[32m    private static void handleListCommand(Task[] taskList, int totalTaskCount) throws NiviException {[m
[32m+[m[32m        if (totalTaskCount == 0) {[m
[32m+[m[32m            throw new NiviException("The list is still empty");[m
         }[m
 [m
         printDivider();[m
         System.out.println(" Here are the tasks in your list:");[m
[31m-        for (int i = 0; i < taskCount; i++) {[m
[31m-            System.out.println(" " + (i + 1) + "." + tasks[i]);[m
[32m+[m[32m        for (int currentTaskPosition = 0; currentTaskPosition < totalTaskCount; currentTaskPosition++) {[m
[32m+[m[32m            System.out.println(" " + (currentTaskPosition + 1) + "." + taskList[currentTaskPosition]);[m
         }[m
         printDivider();[m
[31m-        return taskCount;[m
     }[m
 [m
[31m-    private static void handleMarkCommand(Task[] tasks, int taskCount, String lowerWord) {[m
[31m-        // using a better variable name[m
[31m-        int taskNumber = Integer.parseInt(lowerWord.substring(5).trim());[m
[31m-        int taskIndex = taskNumber - 1;[m
[32m+[m[32m    private static void handleMarkCommand(Task[] taskList, int totalTaskCount,[m[41m [m
[32m+[m[32m            String lowercaseUserInput) throws NiviException {[m
[32m+[m[32m        try {[m
[32m+[m[32m            String taskNumberString = lowercaseUserInput.substring(5).trim();[m
[32m+[m[32m            int userProvidedTaskNumber = Integer.parseInt(taskNumberString);[m
[32m+[m[32m            int arrayIndexOfTask = userProvidedTaskNumber - 1;[m
 [m
[31m-        if (taskIndex >= taskCount || taskIndex < 0) {[m
[31m-            System.out.println("Invalid task number!");[m
[31m-            return;[m
[31m-        }[m
[32m+[m[32m            if (arrayIndexOfTask >= totalTaskCount || arrayIndexOfTask < 0) {[m
[32m+[m[32m                throw new NiviException("Invalid task number!");[m
[32m+[m[32m            }[m
 [m
[31m-        tasks[taskIndex].markAsDone();[m
[31m-        printDivider();[m
[31m-        System.out.println(" Nice! I've marked this task as done:");[m
[31m-        System.out.println("   " + tasks[taskIndex]);[m
[31m-        printDivider();[m
[32m+[m[32m            taskList[arrayIndexOfTask].markAsDone();[m
[32m+[m[32m            printDivider();[m
[32m+[m[32m            System.out.println(" Nice! I've marked this task as done:");[m
[32m+[m[32m            System.out.println("   " + taskList[arrayIndexOfTask]);[m
[32m+[m[32m            printDivider();[m
[32m+[m[32m        } catch (NumberFormatException invalidNumberError) {[m
[32m+[m[32m            throw new NiviException("The task number not valid, give the correct one!");[m
[32m+[m[32m        } catch (StringIndexOutOfBoundsException missingNumberError) {[m
[32m+[m[32m            throw new NiviException("You need to specify which task to mark first, where is ur number!");[m
[32m+[m[32m        }[m
     }[m
 [m
[31m-    private static void handleUnmarkCommand(Task[] tasks, int taskCount, String lowerWord) {[m
[31m-        int taskNumber = Integer.parseInt(lowerWord.substring(7).trim());[m
[31m-        int taskIndex = taskNumber - 1;[m
[32m+[m[32m    private static void handleUnmarkCommand(Task[] taskList, int totalTaskCount,[m[41m [m
[32m+[m[32m            String lowercaseUserInput) throws NiviException {[m
[32m+[m[32m        try {[m
[32m+[m[32m            String taskNumberString = lowercaseUserInput.substring(7).trim();[m
[32m+[m[32m            int userProvidedTaskNumber = Integer.parseInt(taskNumberString);[m
[32m+[m[32m            int arrayIndexOfTask = userProvidedTaskNumber - 1;[m
 [m
[31m-        if (taskIndex >= taskCount || taskIndex < 0) {[m
[31m-            System.out.println("Invalid task number!");[m
[31m-            return;[m
[31m-        }[m
[32m+[m[32m            if (arrayIndexOfTask >= totalTaskCount || arrayIndexOfTask < 0) {[m
[32m+[m[32m                throw new NiviException("Invalid task number!");[m
[32m+[m[32m            }[m
 [m
[31m-        if (!tasks[taskIndex].isDone) {[m
[31m-            System.out.println("This task is not marked as done yet!");[m
[31m-            return;[m
[31m-        }[m
[32m+[m[32m            if (!taskList[arrayIndexOfTask].isDone) {[m
[32m+[m[32m                throw new NiviException("Havent do but want to unmark Aiyaaa!");[m
[32m+[m[32m            }[m
 [m
[31m-        tasks[taskIndex].markAsUndone();[m
[31m-        printDivider();[m
[31m-        System.out.println(" OK, I've marked this task as not done yet:");[m
[31m-        System.out.println("   " + tasks[taskIndex]);[m
[31m-        printDivider();[m
[32m+[m[32m            taskList[arrayIndexOfTask].markAsUndone();[m
[32m+[m[32m            printDivider();[m
[32m+[m[32m            System.out.println(" OK, I've marked this task as not done yet:");[m
[32m+[m[32m            System.out.println("   " + taskList[arrayIndexOfTask]);[m
[32m+[m[32m            printDivider();[m
[32m+[m[32m        } catch (NumberFormatException invalidNumberError) {[m
[32m+[m[32m            throw new NiviException("Invalid task number, provide the correct one !!!");[m
[32m+[m[32m        } catch (StringIndexOutOfBoundsException missingNumberError) {[m
[32m+[m[32m            throw new NiviException("Which one u want to unmark, never say the number? Haiya!!!");[m
[32m+[m[32m        }[m
     }[m
 [m
[31m-    private static int handleAddTask(Task[] tasks, int taskCount, String word, String lowerWord) {[m
[31m-[m
[31m-        if (taskCount >= MAX_TASKS) {[m
[31m-            System.out.println(" Sorry, the list is full!");[m
[31m-            return taskCount;[m
[32m+[m[32m    private static int handleAddTask(Task[] taskList, int totalTaskCount,[m[41m [m
[32m+[m[32m            String userInput, String lowercaseUserInput) throws NiviException {[m
[32m+[m[32m        if (totalTaskCount >= maximumTaskCapacity) {[m
[32m+[m[32m            throw new NiviException("Sorry, the list is full!");[m
         }[m
 [m
[31m-        // when creating every task, i personalize it for every type of task[m
[31m-        if (lowerWord.startsWith("todo")) {[m
[31m-            tasks[taskCount] = createTodoTask(word);[m
[31m-        } else if (lowerWord.startsWith("deadline")) {[m
[31m-            tasks[taskCount] = createDeadlineTask(word);[m
[31m-        } else if (lowerWord.startsWith("event")) {[m
[31m-            tasks[taskCount] = createEventTask(word);[m
[32m+[m[32m        if (lowercaseUserInput.startsWith("todo")) {[m
[32m+[m[32m            taskList[totalTaskCount] = createTodoTask(userInput);[m
[32m+[m[32m        } else if (lowercaseUserInput.startsWith("deadline")) {[m
[32m+[m[32m            taskList[totalTaskCount] = createDeadlineTask(userInput);[m
[32m+[m[32m        } else if (lowercaseUserInput.startsWith("event")) {[m
[32m+[m[32m            taskList[totalTaskCount] = createEventTask(userInput);[m
         }[m
 [m
         printDivider();[m
[31m-        System.out.println(" Got it. I've added this task:");[m
[31m-        System.out.println("   " + tasks[taskCount]);[m
[31m-        taskCount++;[m
[31m-        System.out.println(" Now you have " + taskCount + " tasks in the list.");[m
[32m+[m[32m        System.out.println("Got it. I've added this task:");[m
[32m+[m[32m        System.out.println("   " + taskList[totalTaskCount]);[m
[32m+[m[32m        totalTaskCount++;[m
[32m+[m[32m        System.out.println("OKOK Now you have " + totalTaskCount + " tasks in the list.");[m
         printDivider();[m
 [m
[31m-        return taskCount;[m
[32m+[m[32m        return totalTaskCount;[m
     }[m
 [m
[31m-    private static Task createTodoTask(String word) {[m
[31m-        String activity = word.substring(5).trim();[m
[31m-        return new Todo(activity);[m
[32m+[m[32m    private static Task createTodoTask(String userInput) throws NiviException {[m
[32m+[m[32m        String todoDescription = userInput.substring(5).trim();[m
[32m+[m[32m        if (todoDescription.isEmpty()) {[m
[32m+[m[32m            throw new NiviException("The description of the todo still empty whatt!??!?!?!");[m
[32m+[m[32m        }[m
[32m+[m[32m        return new Todo(todoDescription);[m
     }[m
 [m
[31m-    private static Task createDeadlineTask(String word) {[m
[31m-        String description = word.substring(9).trim();[m
[31m-        String[] parts = description.split(" /by ");[m
[31m-        String activity = parts[0];[m
[31m-        String by = parts[1];[m
[31m-        return new Deadline(activity, by);[m
[32m+[m[32m    private static Task createDeadlineTask(String userInput) throws NiviException {[m
[32m+[m[32m        try {[m
[32m+[m[32m            String fullDeadlineDescription = userInput.substring(9).trim();[m
[32m+[m[32m            if (fullDeadlineDescription.isEmpty()) {[m
[32m+[m[32m                throw new NiviException("Ei u never give description of a deadline properly!!!");[m
[32m+[m[32m            }[m
[32m+[m[41m            [m
[32m+[m[32m            String[] descriptionAndDeadlineParts = fullDeadlineDescription.split(" /by ");[m
[32m+[m[32m            if (descriptionAndDeadlineParts.length < 2) {[m
[32m+[m[32m                throw new NiviException("Big bro need to specify the deadline with /by laa!");[m
[32m+[m[32m            }[m
[32m+[m[41m            [m
[32m+[m[32m            String taskDescription = descriptionAndDeadlineParts[0].trim();[m
[32m+[m[32m            String deadlineTime = descriptionAndDeadlineParts[1].trim();[m
[32m+[m[41m            [m
[32m+[m[32m            if (taskDescription.isEmpty() || deadlineTime.isEmpty()) {[m
[32m+[m[32m                throw new NiviException("Eeeee why the description and the deadline time empty ?!?!");[m
[32m+[m[32m            }[m
[32m+[m[41m            [m
[32m+[m[32m            return new Deadline(taskDescription, deadlineTime);[m
[32m+[m[32m        } catch (ArrayIndexOutOfBoundsException parsingError) {[m
[32m+[m[32m            throw new NiviException("This format is wrong bro the correct one is -->>> deadline <description> /by <time>");[m
[32m+[m[32m        }[m
     }[m
 [m
[31m-    private static Task createEventTask(String word) {[m
[31m-        String description = word.substring(6).trim();[m
[31m-        String[] partsByFrom = description.split(" /from ");[m
[31m-        String activity = partsByFrom[0];[m
[31m-        String[] partsByTo = partsByFrom[1].split(" /to ");[m
[31m-        String from = partsByTo[0];[m
[31m-        String to = partsByTo[1];[m
[31m-        return new Event(activity, from, to);[m
[32m+[m[32m    private static Task createEventTask(String userInput) throws NiviException {[m
[32m+[m[32m        try {[m
[32m+[m[32m            String fullEventDescription = userInput.substring(6).trim();[m
[32m+[m[32m            if (fullEventDescription.isEmpty()) {[m
[32m+[m[32m                throw new NiviException("EIII why the description of the event empty?!?!");[m
[32m+[m[32m            }[m
[32m+[m[41m            [m
[32m+[m[32m            String[] descriptionAndFromParts = fullEventDescription.split(" /from ");[m
[32m+[m[32m            if (descriptionAndFromParts.length < 2) {[m
[32m+[m[32m                throw new NiviException("Haiya need to specify the start time with /from lah!");[m
[32m+[m[32m            }[m
[32m+[m[41m            [m
[32m+[m[32m            String eventDescription = descriptionAndFromParts[0].trim();[m
[32m+[m[32m            String[] fromAndToParts = descriptionAndFromParts[1].split(" /to ");[m
[32m+[m[32m            if (fromAndToParts.length < 2) {[m
[32m+[m[32m                throw new NiviException("Eiii need to specify the end time with /to lah!");[m
[32m+[m[32m            }[m
[32m+[m[41m            [m
[32m+[m[32m            String eventStartTime = fromAndToParts[0].trim();[m
[32m+[m[32m            String eventEndTime = fromAndToParts[1].trim();[m
[32m+[m[41m            [m
[32m+[m[32m            if (eventDescription.isEmpty() || eventStartTime.isEmpty() || eventEndTime.isEmpty()) {[m
[32m+[m[32m                throw new NiviException("Description, start time, and end time cannot be empty laa aiyooo!");[m
[32m+[m[32m            }[m
[32m+[m[41m            [m
[32m+[m[32m            return new Event(eventDescription, eventStartTime, eventEndTime);[m
[32m+[m[32m        } catch (ArrayIndexOutOfBoundsException parsingError) {[m
[32m+[m[32m            throw new NiviException("Oii cannot laa wrong format!!! should be like this --->>> event <description> /from <start> /to <end>");[m
[32m+[m[32m        }[m
     }[m
 [m
     private static void printDivider() {[m
[31m-        System.out.println(DIVIDER);[m
[32m+[m[32m        System.out.println(lineDivider);[m
     }[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/src/main/java/NiviException.class b/src/main/java/NiviException.class[m
[1mnew file mode 100644[m
[1mindex 0000000..a12348f[m
Binary files /dev/null and b/src/main/java/NiviException.class differ
[1mdiff --git a/src/main/java/NiviException.java b/src/main/java/NiviException.java[m
[1mnew file mode 100644[m
[1mindex 0000000..0daa257[m
[1m--- /dev/null[m
[1m+++ b/src/main/java/NiviException.java[m
[36m@@ -0,0 +1,5 @@[m
[32m+[m[32mpublic class NiviException extends Exception {[m
[32m+[m[32m    public NiviException(String message) {[m
[32m+[m[32m        super(message);[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
\ No newline at end of file[m
