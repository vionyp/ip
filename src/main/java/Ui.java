import java.util.Scanner;

/**
 * Ui class provide methods to interact with the user, such as reading user input and printing messages.
 */
public class Ui {
    private static final String LINE_DIVIDER = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Initializes the Scanner object to read user input from console. 
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Return the user input with String datatype.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Stop program from reading user input, preventing resource leak.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Print the message with a line divider before and after the message for readability.
     * @param message String that contains the message to be printed
     */
    public void printMessage(String message) {
        System.out.println(LINE_DIVIDER);
        System.out.println(message);
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Print the welcome message when the program starts,such as theNivi logo and a "Hello" message.
     */
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

    /**
     * Print the goodbye message when the user exits the program.
     */
    public void printBye() {
        printMessage(" Bye. See you soon little kid! Love u");
    }

    /**
     * Print the error message when there is an exception, such as invalid user input or errors while saving/loading data.
     * @param message String that contains the error message to be printed
     */
    public void printError(String message) {
        printMessage(" " + message);
    }
}