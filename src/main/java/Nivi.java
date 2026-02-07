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
        while (true){
            String word = inStr.nextLine();
            String lowerWord = word.toLowerCase();

            if (lowerWord.contains("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. See you soon little kid! Love u");
                System.out.println("____________________________________________________________");
                break;
            }else if (lowerWord.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    // Menampilkan nomor dan deskripsi dari class Task
                    System.out.println((i + 1) + ". " + tasks[i].getDescription());
                }
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