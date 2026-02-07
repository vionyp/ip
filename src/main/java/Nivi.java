
import java.util.Scanner;

public class Nivi {
    // simplify the code that have repetitions like the line divider
    private static final int MAX_TASKS = 100;
    private static final String DIVIDER = "____________________________________________________________";

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        String logo = " _   _  _____ __      __ _____\n"
                + "| \\ | ||_   _|\\ \\    / /|_   _|\n"
                + "|  \\| |  | |   \\ \\  / /   | |  \n"
                + "| . ` |  | |    \\ \\/ /    | |  \n"
                + "| |\\  | _| |_    \\  /    _| |_ \n"
                + "|_| \\_||_____|    \\/    |_____|\n";

        System.out.println("Hello from\n" + logo);
        printDivider();
        System.out.println(" Hello! I'm NIVI");
        System.out.println(" What can I do for you?");
        printDivider();

        Scanner inStr = new Scanner(System.in);
        while (true) {
            String word = inStr.nextLine();
            String lowerWord = word.toLowerCase();

            // structuring the code logically
            // give a space and enter to differentiate each if else case
            if (lowerWord.contains("bye")) {
                printDivider();
                System.out.println(" Bye. See you soon little kid! Love u");
                printDivider();
                break;
            }

            if (lowerWord.equals("list")) {
                taskCount = handleListCommand(tasks, taskCount);
                continue;
            }

            if (lowerWord.startsWith("mark ")) {
                handleMarkCommand(tasks, taskCount, lowerWord);
                continue;
            }

            if (lowerWord.startsWith("unmark ")) {
                handleUnmarkCommand(tasks, taskCount, lowerWord);
                continue;
            }

            if (lowerWord.startsWith("todo") || lowerWord.startsWith("deadline") || lowerWord.startsWith("event")) {
                taskCount = handleAddTask(tasks, taskCount, word, lowerWord);
                continue;
            }

            printDivider();
            System.out.println(" Please give a specific command: todo/deadline/event/list/mark/unmark/bye");
            printDivider();
        }
        inStr.close();
    }

    // avoid long methods and deep nesting because of the if else
    // so every if else will call another function below
    private static int handleListCommand(Task[] tasks, int taskCount) {
        // IMPROVED: Early return for edge case (W4.6l - happy path prominent)
        if (taskCount == 0) {
            System.out.println("The list is still empty");
            return taskCount;
        }

        printDivider();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
        printDivider();
        return taskCount;
    }

    private static void handleMarkCommand(Task[] tasks, int taskCount, String lowerWord) {
        // using a better variable name
        int taskNumber = Integer.parseInt(lowerWord.substring(5).trim());
        int taskIndex = taskNumber - 1;

        if (taskIndex >= taskCount || taskIndex < 0) {
            System.out.println("Invalid task number!");
            return;
        }

        tasks[taskIndex].markAsDone();
        printDivider();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks[taskIndex]);
        printDivider();
    }

    private static void handleUnmarkCommand(Task[] tasks, int taskCount, String lowerWord) {
        int taskNumber = Integer.parseInt(lowerWord.substring(7).trim());
        int taskIndex = taskNumber - 1;

        if (taskIndex >= taskCount || taskIndex < 0) {
            System.out.println("Invalid task number!");
            return;
        }

        if (!tasks[taskIndex].isDone) {
            System.out.println("This task is not marked as done yet!");
            return;
        }

        tasks[taskIndex].markAsUndone();
        printDivider();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks[taskIndex]);
        printDivider();
    }

    private static int handleAddTask(Task[] tasks, int taskCount, String word, String lowerWord) {

        if (taskCount >= MAX_TASKS) {
            System.out.println(" Sorry, the list is full!");
            return taskCount;
        }

        // when creating every task, i personalize it for every type of task
        if (lowerWord.startsWith("todo")) {
            tasks[taskCount] = createTodoTask(word);
        } else if (lowerWord.startsWith("deadline")) {
            tasks[taskCount] = createDeadlineTask(word);
        } else if (lowerWord.startsWith("event")) {
            tasks[taskCount] = createEventTask(word);
        }

        printDivider();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks[taskCount]);
        taskCount++;
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        printDivider();

        return taskCount;
    }

    private static Task createTodoTask(String word) {
        String activity = word.substring(5).trim();
        return new Todo(activity);
    }

    private static Task createDeadlineTask(String word) {
        String description = word.substring(9).trim();
        String[] parts = description.split(" /by ");
        String activity = parts[0];
        String by = parts[1];
        return new Deadline(activity, by);
    }

    private static Task createEventTask(String word) {
        String description = word.substring(6).trim();
        String[] partsByFrom = description.split(" /from ");
        String activity = partsByFrom[0];
        String[] partsByTo = partsByFrom[1].split(" /to ");
        String from = partsByTo[0];
        String to = partsByTo[1];
        return new Event(activity, from, to);
    }

    private static void printDivider() {
        System.out.println(DIVIDER);
    }
}