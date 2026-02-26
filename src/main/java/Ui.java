import java.util.Scanner;

public class Ui {
    private static final String LINE_DIVIDER = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public void printMessage(String message) {
        System.out.println(LINE_DIVIDER);
        System.out.println(message);
        System.out.println(LINE_DIVIDER);
    }

    public void printStart() {
        String niviLogo = " _   _  _____ __      __ _____\n"
                + "| \\ | ||_   _|\\ \\    / /|_   _|\n"
                + "|  \\| |  | |   \\ \\  / /   | |  \n"
                + "| . ` |  | |    \\ \\/ /    | |  \n"
                + "| |\\  | _| |_    \\  /    _| |_ \n"
                + "|_| \\_||_____|    \\/    |_____|\n";
        System.out.println("Hello from\n" + niviLogo);
        printMessage(" Hello! I'm NIVI\n What can I do for you , little kid?");
    }

    public void printBye() {
        printMessage(" Bye. See you soon little kid! Love u");
    }

    public void printError(String message) {
        printMessage(" " + message);
    }
}