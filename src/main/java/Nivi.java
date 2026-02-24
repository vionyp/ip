import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Nivi {
    private static final String FILE_PATH = "./data/nivi.txt";
    private static Storage storage = new Storage(FILE_PATH);
    private static ArrayList<Task> taskList = new ArrayList<>();
    
    private static final String lineDivider = "____________________________________________________________";

    private static Scanner userInputScanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Loading the data from file
        try {
            taskList = storage.load();
        } catch (FileNotFoundException e) {
            taskList = new ArrayList<>();
        } catch (Exception e) {
            System.out.println(" Error loading file: " + e.getMessage());
            taskList = new ArrayList<>();
        }
        printStart();

        while (true) {
            try {
                String userInput = userInputScanner.nextLine();
                String lowercaseUserInput = userInput.toLowerCase();

                // structuring the code logically
                // give a space and enter to differentiate each if else case
                if (lowercaseUserInput.contains("bye")) {
                    printMessageInsideLine(" Bye. See you soon little kid! Love u");
                    break;
                }

                else if (lowercaseUserInput.equals("list")) {
                    handleListCommand(taskList);
                }

                else if (lowercaseUserInput.startsWith("mark")) {
                    handleMarkCommand(taskList, lowercaseUserInput);
                }

                else if (lowercaseUserInput.startsWith("unmark")) {
                    handleUnmarkCommand(taskList, lowercaseUserInput);
                }

                else if (lowercaseUserInput.startsWith("delete")) {
                    handleDeleteCommand(taskList, lowercaseUserInput);
                }

                else if (lowercaseUserInput.startsWith("todo") || lowercaseUserInput.startsWith("deadline") || lowercaseUserInput.startsWith("event")) {
                    handleAddTask(taskList, userInput, lowercaseUserInput);
                }
                else {
                    throw new NiviException("Little kid, You are required to give a specific command: todo/deadline/event/list/mark/unmark/bye");
                }  
        
                saveData();

            } catch (NiviException niviError) {
                printMessageInsideLine(" " + niviError.getMessage());
            } catch (Exception unexpectedError) {
                printMessageInsideLine(" Error: " + unexpectedError.getMessage());
            }
        }
        userInputScanner.close();
    }


    private static void saveData() {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            System.out.println(" Sorry kid, you fail to save the file!");
        }
    }
    // Avoid long methods and deep nesting because of the if else
    // So every if else will call another function below
    private static void handleListCommand(ArrayList<Task> taskList) throws NiviException {
        if (taskList.isEmpty()) {
            throw new NiviException("The list is still empty");
        }

        String message = " Here are the tasks in your list:\n";
        for (int currentTaskPosition = 0; currentTaskPosition < taskList.size(); currentTaskPosition++) {
            message += " " + (currentTaskPosition + 1) + "." + taskList.get(currentTaskPosition) + "\n";
        }
        printMessageInsideLine(message);
    }

    private static void handleMarkCommand(ArrayList<Task> taskList, 
            String lowercaseUserInput) throws NiviException {
        // using a better variable name
        try {
            String trimmed = lowercaseUserInput.trim();
            if (trimmed.equals("mark")) {
                throw new NiviException("You need to specify which task to mark first, where is ur number!");
            }
            int userProvidedTaskNumber = Integer.parseInt(trimmed.substring(5));
            int arrayIndexOfTask = userProvidedTaskNumber - 1;

            if (arrayIndexOfTask >= taskList.size() || arrayIndexOfTask < 0) {
                throw new NiviException("Invalid task number!");
            }

            taskList.get(arrayIndexOfTask).markAsDone();
            printMessageInsideLine(" Nice! I've marked this task as done:\n" + "   " + taskList.get(arrayIndexOfTask));
        } catch (NumberFormatException invalidNumberError) {
            throw new NiviException("The task number not valid, give the correct one!");
        }
    }

    private static void handleUnmarkCommand(ArrayList<Task> taskList, 
            String lowercaseUserInput) throws NiviException {
        try {
            String trimmed = lowercaseUserInput.trim();
            if (trimmed.equals("unmark")) {
                throw new NiviException("You need to specify which task to unmark first, where is ur number!");
            }
            int userProvidedTaskNumber = Integer.parseInt(trimmed.substring(7));
            int arrayIndexOfTask = userProvidedTaskNumber - 1;

            if (arrayIndexOfTask >= taskList.size() || arrayIndexOfTask < 0) {
                throw new NiviException("Invalid task number!");
            }

            if (!taskList.get(arrayIndexOfTask).getIsDone()) {
                throw new NiviException("Havent do but want to unmark Aiyaaa!");
            }

            taskList.get(arrayIndexOfTask).markAsUndone();
            printMessageInsideLine(" OK, I've marked this task as not done yet:\n" + "   " + taskList.get(arrayIndexOfTask));
        } catch (NumberFormatException invalidNumberError) {
            throw new NiviException("Invalid task number, provide the correct one !!!");
        } 
    }

    private static void handleDeleteCommand(ArrayList<Task> taskList,
            String lowercaseUserInput) throws NiviException {
        try {
            String trimmed = lowercaseUserInput.trim();
            if (trimmed.equals("delete")) {
                throw new NiviException("You need to specify which task to delete!, Aiyaaa cannot just delete delete like that!");
            }
            int userProvidedTaskNumber = Integer.parseInt(trimmed.substring(7));
            int arrayIndexOfTask = userProvidedTaskNumber - 1;

            if (arrayIndexOfTask >= taskList.size() || arrayIndexOfTask < 0) {
                throw new NiviException("Invalid task number!");
            }

            Task deletedTask = taskList.get(arrayIndexOfTask);
            taskList.remove(arrayIndexOfTask);

            printMessageInsideLine(" Okk. I've deleted this task, yaaa:\n" + "   " + deletedTask + "\n" +
                " Now you have " + taskList.size() + " tasks in the list.");
        } catch (NumberFormatException invalidNumberError) {
            throw new NiviException("Invalid task number, u need to input the correct one eiiii!");
        }
    }

    private static void handleAddTask(ArrayList<Task> taskList,
            String userInput, String lowercaseUserInput) throws NiviException {
        
        Task newTask = null;
        if (lowercaseUserInput.startsWith("todo")) {
            newTask = createTodoTask(userInput);
        } else if (lowercaseUserInput.startsWith("deadline")) {
            newTask = createDeadlineTask(userInput);
        } else if (lowercaseUserInput.startsWith("event")) {
            newTask = createEventTask(userInput);
        }

        taskList.add(newTask);
        printMessageInsideLine(" Got it. I've added this task:\n" + "   " + newTask + "\n" + " Now you have " + taskList.size() + " tasks in the list.");

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

    public static void printStart(){
        String niviLogo = " _   _  _____ __      __ _____\n"
                + "| \\ | ||_   _|\\ \\    / /|_   _|\n"
                + "|  \\| |  | |   \\ \\  / /   | |  \n"
                + "| . ` |  | |    \\ \\/ /    | |  \n"
                + "| |\\  | _| |_    \\  /    _| |_ \n"
                + "|_| \\_||_____|    \\/    |_____|\n";

        System.out.println("Hello from\n" + niviLogo);
        printMessageInsideLine(" Hello! I'm NIVI\n" +
            " What can I do for you , little kid?"
        );
    }

    private static void printMessageInsideLine(String message){
        System.out.println(lineDivider);
        System.out.println(message);
        System.out.println(lineDivider);
    }
}

class NiviException extends Exception {
    public NiviException(String message) {
        super(message);
    }
}