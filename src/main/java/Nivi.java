
import java.util.Scanner;

public class Nivi {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String logo = " _   _  _____ __      __ _____\n"
                + "| \\ | ||_   _|\\ \\    / /|_   _|\n"
                + "|  \\| |  | |   \\ \\  / /   | |  \n"
                + "| . ` |  | |    \\ \\/ /    | |  \n"
                + "| |\\  | _| |_    \\  /    _| |_ \n"
                + "|_| \\_||_____|    \\/    |_____|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm NIVI");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner inStr = new Scanner(System.in);
        while (true) {
            String word = inStr.nextLine();
            String lowerWord = word.toLowerCase();

            if (lowerWord.contains("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. See you soon little kid! Love u");
                System.out.println("____________________________________________________________");
                break;
            } else if (lowerWord.equals("list")) {
                if (taskCount == 0){
                    System.out.println("The list is still empty");
                    continue;
                }
                System.out.println("____________________________________________________________");
                System.out.println("Here are all the task you have added:");
                for (int i = 0; i < taskCount; i++) {
                    // Menampilkan nomor dan deskripsi dari class Task
                    System.out.println((i+1)+". "+tasks[i]);
                }
                System.out.println("____________________________________________________________");
            }else if(lowerWord.startsWith("todo")||lowerWord.startsWith("deadline")||lowerWord.startsWith("event")){
                if (taskCount < 100) {
                    if (lowerWord.startsWith("todo")) {
                        // the activity without deadline
                        String activity = word.substring(5).trim(); // Get everything after "todo "
                        tasks[taskCount] = new Todo(activity);
                    } else if (lowerWord.startsWith("deadline")) {
                        // the activity with deadline
                        String description = word.substring(9).trim(); // Get everything after "deadline "
                        String[] parts = description.split(" /by ");
                        String activity = parts[0];
                        String by = parts[1];
                        tasks[taskCount] = new Deadline(activity, by);
                    } else if (lowerWord.startsWith("event")) {
                        // the activity with start and end time
                        String description = word.substring(6).trim(); // Get everything after "event "
                        String[] partsByFrom = description.split(" /from ");
                        String activity = partsByFrom[0];
                        String[] partsByTo = partsByFrom[1].split(" /to ");
                        String from = partsByTo[0];
                        String to = partsByTo[1];
                        tasks[taskCount] = new Event(activity, from, to);
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount]);
                    taskCount++;
                    System.out.println("Now you have "+taskCount+" in the list.");
                    System.out.println("____________________________________________________________");

                } else {
                    System.out.println(" Sorry, the list is full!");
                }
            }else if(lowerWord.startsWith("mark")){
                int taskIndex = Integer.parseInt(lowerWord.substring(5).trim())-1; // since mark is until index 3 then there will be a spece in index 4. Thus the index will bee on index 5
                if (taskIndex>=taskCount){
                    System.out.println("You never listed until that number");
                    continue;
                }
                tasks[taskIndex].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Well, well! maybe you got a lot in your list, at least, you've done thsi one:");
                System.out.println("   [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                System.out.println("____________________________________________________________");

            }else if(lowerWord.startsWith("unmark")){
                int taskIndex = Integer.parseInt(lowerWord.substring(7).trim())-1; // since unmark is until index 5 then there will be a spece in index 6. Thus the index will bee on index 7
                if (taskIndex>=taskCount){
                    System.out.println("You never listed until that number");
                    continue;
                }
                if (!tasks[taskIndex].isDone){
                    System.out.println("You never actly mark this activity");
                    continue;
                }
                tasks[taskIndex].markAsUndone();
                System.out.println("____________________________________________________________");
                System.out.println(" Opps, turns out I haven't done this one completely:");
                System.out.println("   [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                System.out.println("____________________________________________________________");
            }else {
                System.out.println("____________________________________________________________");
                System.out.println("Please give a specific comment!! is it todo/deadline/event. Thank you!!");
                System.out.println("____________________________________________________________");
            }
        }
        inStr.close();
    }
}