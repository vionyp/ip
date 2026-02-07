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
                System.out.println("____________________________________________________________");
                System.out.println("Here are all the task you have added:");
                for (int i = 0; i < taskCount; i++) {
                    // Menampilkan nomor dan deskripsi dari class Task
                    System.out.println((i + 1) + ". " + "[" + tasks[i].getStatusIcon() + "]" + tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________");
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
                if (taskCount < 100) {
                    tasks[taskCount] = new Task(word);
                    taskCount++;
                    System.out.println(" added: " + word);
                } else {
                    System.out.println(" Sorry, the list is full!");
                }
                System.out.println("____________________________________________________________");
            }
        }
        inStr.close();
    }
}