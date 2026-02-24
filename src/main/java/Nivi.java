import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

import java.util.Scanner;

public class Nivi {
    // simplify the code that have repetitions like the line divider
    private static final int maximumTaskCapacity = 100;
    private static final String lineDivider = "____________________________________________________________";

    public static void main(String[] args) {
        Task[] taskList = new Task[maximumTaskCapacity];
        int totalTaskCount = 0;

        String niviLogo = " _   _  _____ __      __ _____\n"
                + "| \\ | ||_   _|\\ \\    / /|_   _|\n"
                + "|  \\| |  | |   \\ \\  / /   | |  \n"
                + "| . ` |  | |    \\ \\/ /    | |  \n"
                + "| |\\  | _| |_    \\  /    _| |_ \n"
                + "|_| \\_||_____|    \\/    |_____|\n";

        System.out.println("Hello from\n" + niviLogo);
        printDivider();
        System.out.println(" Hello! I'm NIVI");
        System.out.println(" What can I do for you , little kid?");
        printDivider();

        Scanner userInputScanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = userInputScanner.nextLine();
                String lowercaseUserInput = userInput.toLowerCase();

                // structuring the code logically
                // give a space and enter to differentiate each if else case
                if (lowercaseUserInput.contains("bye")) {
                    printDivider();
                    System.out.println(" Bye. See you soon little kid! Love u");
                    printDivider();
                    break;
                }

                if (lowercaseUserInput.equals("list")) {
                    handleListCommand(taskList, totalTaskCount);
                    continue;
                }

                if (lowercaseUserInput.startsWith("mark ")) {
                    handleMarkCommand(taskList, totalTaskCount, lowercaseUserInput);
                    continue;
                }

                if (lowercaseUserInput.startsWith("unmark ")) {
                    handleUnmarkCommand(taskList, totalTaskCount, lowercaseUserInput);
                    continue;
                }

                if (lowercaseUserInput.startsWith("todo") || lowercaseUserInput.startsWith("deadline") 
                        || lowercaseUserInput.startsWith("event")) {
                    totalTaskCount = handleAddTask(taskList, totalTaskCount, userInput, lowercaseUserInput);
                    continue;
                }

                throw new NiviException("Little kid, You are required to give a specific command: todo/deadline/event/list/mark/unmark/bye");

            } catch (NiviException niviError) {
                printDivider();
                System.out.println(" " + niviError.getMessage());
                printDivider();
            } catch (Exception unexpectedError) {
                printDivider();
                System.out.println(" Error: " + unexpectedError.getMessage());
                printDivider();
            }
        }
        userInputScanner.close();
    }

    // avoid long methods and deep nesting because of the if else
    // so every if else will call another function below
    private static void handleListCommand(Task[] taskList, int totalTaskCount) throws NiviException {
        // IMPROVED: Early return for edge case (W4.6l - happy path prominent)
        if (totalTaskCount == 0) {
            throw new NiviException("The list is still empty");
        }

        printDivider();
        System.out.println(" Here are the tasks in your list:");
        for (int currentTaskPosition = 0; currentTaskPosition < totalTaskCount; currentTaskPosition++) {
            System.out.println(" " + (currentTaskPosition + 1) + "." + taskList[currentTaskPosition]);
        }
        printDivider();
    }

    private static void handleMarkCommand(Task[] taskList, int totalTaskCount, 
            String lowercaseUserInput) throws NiviException {
        // using a better variable name
        try {
            int userProvidedTaskNumber = Integer.parseInt(lowercaseUserInput.substring(5).trim());
            int arrayIndexOfTask = userProvidedTaskNumber - 1;

            if (arrayIndexOfTask >= totalTaskCount || arrayIndexOfTask < 0) {
                throw new NiviException("Invalid task number!");
            }

            taskList[arrayIndexOfTask].markAsDone();
            printDivider();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + taskList[arrayIndexOfTask]);
            printDivider();
        } catch (NumberFormatException invalidNumberError) {
            throw new NiviException("The task number not valid, give the correct one!");
        } catch (StringIndexOutOfBoundsException missingNumberError) {
            throw new NiviException("You need to specify which task to mark first, where is ur number!");
        }
    }

    private static void handleUnmarkCommand(Task[] taskList, int totalTaskCount, 
            String lowercaseUserInput) throws NiviException {
        try {
            int userProvidedTaskNumber = Integer.parseInt(lowercaseUserInput.substring(7).trim());
            int arrayIndexOfTask = userProvidedTaskNumber - 1;

            if (arrayIndexOfTask >= totalTaskCount || arrayIndexOfTask < 0) {
                throw new NiviException("Invalid task number!");
            }

            if (!taskList[arrayIndexOfTask].isDone) {
                throw new NiviException("Havent do but want to unmark Aiyaaa!");
            }

            taskList[arrayIndexOfTask].markAsUndone();
            printDivider();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + taskList[arrayIndexOfTask]);
            printDivider();
        } catch (NumberFormatException invalidNumberError) {
            throw new NiviException("Invalid task number, provide the correct one !!!");
        } catch (StringIndexOutOfBoundsException missingNumberError) {
            throw new NiviException("Which one u want to unmark, never say the number? Haiya!!!");
        }
    }

    private static int handleAddTask(Task[] taskList, int totalTaskCount, 
            String userInput, String lowercaseUserInput) throws NiviException {
        if (totalTaskCount >= maximumTaskCapacity) {
            throw new NiviException("Sorry, the list is full!");
        }

        // when creating every task, i personalize it for every type of task
        if (lowercaseUserInput.startsWith("todo")) {
            taskList[totalTaskCount] = createTodoTask(userInput);
        } else if (lowercaseUserInput.startsWith("deadline")) {
            taskList[totalTaskCount] = createDeadlineTask(userInput);
        } else if (lowercaseUserInput.startsWith("event")) {
            taskList[totalTaskCount] = createEventTask(userInput);
        }

        printDivider();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + taskList[totalTaskCount]);
        totalTaskCount++;
        System.out.println(" Now you have " + totalTaskCount + " tasks in the list.");
        printDivider();

        return totalTaskCount;
    }

    private static Task createTodoTask(String userInput) throws NiviException {
        String todoDescription = userInput.substring(5).trim();
        if (todoDescription.isEmpty()) {
            throw new NiviException("The description of the todo still empty whatt!??!?!?!");
        }
        return new Todo(todoDescription);
    }

    private static Task createDeadlineTask(String userInput) throws NiviException {
        try {
            String fullDeadlineDescription = userInput.substring(9).trim();
            if (fullDeadlineDescription.isEmpty()) {
                throw new NiviException("Ei u never give description of a deadline properly!!!");
            }
            
            String[] descriptionAndDeadlineParts = fullDeadlineDescription.split(" /by ");
            if (descriptionAndDeadlineParts.length < 2) {
                throw new NiviException("Big bro need to specify the deadline with /by laa!");
            }
            
            String taskDescription = descriptionAndDeadlineParts[0].trim();
            String deadlineTime = descriptionAndDeadlineParts[1].trim();
            
            if (taskDescription.isEmpty() || deadlineTime.isEmpty()) {
                throw new NiviException("Eeeee why the description and the deadline time empty ?!?!");
            }
            
            return new Deadline(taskDescription, deadlineTime);
        } catch (ArrayIndexOutOfBoundsException parsingError) {
            throw new NiviException("This format is wrong bro the correct one is -->>> deadline <description> /by <time>");
        }
    }

    private static Task createEventTask(String userInput) throws NiviException {
        try {
            String fullEventDescription = userInput.substring(6).trim();
            if (fullEventDescription.isEmpty()) {
                throw new NiviException("EIII why the description of the event empty?!?!");
            }
            
            String[] descriptionAndFromParts = fullEventDescription.split(" /from ");
            if (descriptionAndFromParts.length < 2) {
                throw new NiviException("Haiya need to specify the start time with /from lah!");
            }
            
            String eventDescription = descriptionAndFromParts[0].trim();
            String[] fromAndToParts = descriptionAndFromParts[1].split(" /to ");
            if (fromAndToParts.length < 2) {
                throw new NiviException("Eiii need to specify the end time with /to lah!");
            }
            
            String eventStartTime = fromAndToParts[0].trim();
            String eventEndTime = fromAndToParts[1].trim();
            
            if (eventDescription.isEmpty() || eventStartTime.isEmpty() || eventEndTime.isEmpty()) {
                throw new NiviException("Description, start time, and end time cannot be empty laa aiyooo!");
            }
            
            return new Event(eventDescription, eventStartTime, eventEndTime);
        } catch (ArrayIndexOutOfBoundsException parsingError) {
            throw new NiviException("Oii cannot laa wrong format!!! should be like this --->>> event <description> /from <start> /to <end>");
        }
    }

    private static void printDivider() {
        System.out.println(lineDivider);
    }
}