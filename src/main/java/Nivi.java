import java.util.Scanner;

public class Nivi {
    public static void main(String[] args) {
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

        Scanner in = new Scanner(System.in);
        while (true){
            String word = in.nextLine();
            if (word.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. See you soon little kid!");
                System.out.println("____________________________________________________________");
                break;
            }else{
                System.out.println("____________________________________________________________");
                System.out.println(word);
                System.out.println("____________________________________________________________");
            }
        }
    }
}