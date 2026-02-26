import task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



public class Nivi {
    private static final String FILE_PATH = "./data/nivi.txt";
    private static Storage storage = new Storage(FILE_PATH);
    private static TaskList taskList;
    private static Ui ui = new Ui();
    public static void main(String[] args) {
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        } catch (Exception e) {
            ui.printError(" Error loading file: " + e.getMessage());
            taskList = new TaskList();
        }

        ui.printStart();

        while (true) {
            try {
                String userInput = ui.readCommand();
                String lower = userInput.toLowerCase();

                if (lower.contains("bye")) {
                    ui.printBye();
                    break;
                }

                else if (lower.equals("list")) {
                    handleList();
                }

                else if (lower.startsWith("mark")) {
                    handleMark(lower);
                }

                else if (lower.startsWith("unmark")) {
                    handleUnmark(lower);

                }

                else if (lower.startsWith("delete")) {
                    handleDelete(lower);
                }

                else if (lower.startsWith("find")) {
                    handleFind(lower);
                }

                else if (lower.startsWith("todo") || lower.startsWith("deadline") || lower.startsWith("event")) {
                    handleAddTask(userInput, lower);
                }
                else {
                    throw new NiviException("Little kid, You are required to give a specific command: todo/deadline/event/list/mark/unmark/bye");
                }  
        
                saveData();

            } catch (NiviException niviError) {
                ui.printError(niviError.getMessage());
            } catch (Exception unexpectedError) {
                ui.printError(" Error: " + unexpectedError.getMessage());
            }
        }
        
        ui.close();

    }


    private static void saveData() {
        try {
            storage.save(taskList.getTasks());
        } catch (IOException e) {
            ui.printError(" Sorry kid, you fail to save the file!");
        }
    }
    
    private static void handleList() throws NiviException {
        if (taskList.isEmpty()) {
            throw new NiviException("The list is still empty");
        }
        String message = " Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            message += " " + (i + 1) + "." + taskList.get(i) + "\n";
        }
        ui.printMessage(message);
    }

    private static void handleMark(String lower) throws NiviException {
        try {
            String trimmed = lower.trim();
            if (trimmed.equals("mark")) {
                throw new NiviException("You need to specify which task to mark first, where is ur number!");
            }
            int index = Integer.parseInt(trimmed.substring(5)) - 1;
            if (index >= taskList.size() || index < 0) {
                throw new NiviException("Invalid task number!");
            }
            taskList.get(index).markAsDone();
            ui.printMessage(" Nice! I've marked this task as done:\n   " + taskList.get(index));
        } catch (NumberFormatException e) {
            throw new NiviException("The task number not valid, give the correct one!");
        }
    }

    private static void handleUnmark(String lower) throws NiviException {
        try {
            String trimmed = lower.trim();
            if (trimmed.equals("unmark")) {
                throw new NiviException("You need to specify which task to unmark first, where is ur number!");
            }
            int index = Integer.parseInt(trimmed.substring(7)) - 1;
            if (index >= taskList.size() || index < 0) {
                throw new NiviException("Invalid task number!");
            }
            if (!taskList.get(index).getIsDone()) {
                throw new NiviException("Havent do but want to unmark Aiyaaa!");
            }
            taskList.get(index).markAsUndone();
            ui.printMessage(" OK, I've marked this task as not done yet:\n   " + taskList.get(index));
        } catch (NumberFormatException e) {
            throw new NiviException("Invalid task number, provide the correct one !!!");
        }
    }

    private static void handleDelete(String lower) throws NiviException {
        try {
            String trimmed = lower.trim();
            if (trimmed.equals("delete")) {
                throw new NiviException("You need to specify which task to delete!, Aiyaaa cannot just delete delete like that!");
            }
            int index = Integer.parseInt(trimmed.substring(7)) - 1;
            if (index >= taskList.size() || index < 0) {
                throw new NiviException("Invalid task number!");
            }
            Task deleted = taskList.remove(index);
            ui.printMessage(" Okk. I've deleted this task, yaaa:\n   " + deleted + "\n Now you have " + taskList.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new NiviException("Invalid task number, u need to input the correct one eiiii!");
        }
    }

    private static void handleAddTask(String userInput, String lower) throws NiviException {
        Task newTask;
        if (lower.startsWith("todo")) {
            newTask = Parser.parseTodo(userInput);
        } else if (lower.startsWith("deadline")) {
            newTask = Parser.parseDeadline(userInput);
        } else {
            newTask = Parser.parseEvent(userInput);
        }

        taskList.add(newTask);
        ui.printMessage(" Got it. I've added this task:\n   " + newTask + "\n Now you have " + taskList.size() + " tasks in the list.");
    }

    private static void handleFind(String lower) throws NiviException {
        String trimmed = lower.trim();
        if (trimmed.equals("find")) {
            throw new NiviException("Ei what do you want to find? Give me keyword lahhh, u think i can guess liddat?!");
        }
        String keyword = trimmed.substring(5).trim();
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            throw new NiviException("Got nothing liddat eh!");
        }
        String message = " Matched things I can find:\n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            message += " " + (i + 1) + "." + matchingTasks.get(i) + "\n";
        }
        ui.printMessage(message);
    }
}

class NiviException extends Exception {
    public NiviException(String message) {
        super(message);
    }
}