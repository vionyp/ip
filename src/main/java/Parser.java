import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.time.format.DateTimeParseException;

/**
 * Parser class allow user to convert the user input into Task datatype, which can be added into the task list. There are 3 methods in this class, which are parseTodo, parseDeadline, and parseEvent. Each method is responsible for parsing the user input for the corresponding task type and returning a Task object. If the user input is invalid or does not follow the expected format, the methods will throw a NiviException with a specified error message to guide user to correct the input.
 * 
 */
public class Parser {

    /**
     * Parse the user input for a todo task and return a Todo object. 
     * <p>
     * Format : "todo" followed by the description of the task. The description cannot be empty.
     * @param userInput inputed String that starts with "todo".
     * @return a Todo object with the extracted description from the user input.
     * @throws NiviException when the description of the todo task is empty or invalid.
     */
    public static Task parseTodo(String userInput) throws NiviException {
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            throw new NiviException("The description of the todo still empty whatt!??!?!?!");
        }
        return new Todo(description);
    }

    /**
     * Parse the user input for a deadline task and return a Deadline object.
     * <p>
     * Format : "deadline" followed by the description of the task, then "/by" and time deadline.
     * @param userInput inputed String that starts with "deadline" and contains the description and deadline time.
     * @return a Deadline object with the extracted description and deadline time from the user input.
     * @throws NiviException when the description or deadline time is empty or invalid, or does not contain "/by".
     */
    public static Task parseDeadline(String userInput) throws NiviException {
        try {
            String full = userInput.substring(9).trim();
            if (full.isEmpty()) {
                throw new NiviException("Ei u never give description of a deadline properly!!!");
            }
            String[] parts = full.split(" /by ");
            if (parts.length < 2) {
                throw new NiviException("Big bro need to specify the deadline with /by laa!");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new NiviException("Eeeee why the description and the deadline time empty ?!?!");
            }
            return new Deadline(description, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NiviException("This format is wrong bro the correct one is -->>> deadline <description> /by <time>");
        } catch (DateTimeParseException e) {
            throw new NiviException("Aiyo the date format is wrong bro. Try use YYYY-MM-DD, 2019-12-02 liddat");
        }
    }

    /**
     * Parse the user input for an event task and return an Event object.
     * <p>
     * Format : "event" followed by the description of the task, then "/from" and start time, then "/to" and finish time.
     * @param userInput inputed String that starts with "event" and contains the description, start time, and finish time.
     * @return an Event object with the extracted description, start time, and finish time from the user input.
     * @throws NiviException when the description, start time, or finish time is empty or invalid, or does not contain "/from" and "/to".
     */
    public static Task parseEvent(String userInput) throws NiviException {
        try {
            String full = userInput.substring(6).trim();
            if (full.isEmpty()) {
                throw new NiviException("EIII why the description of the event empty?!?!");
            }
            String[] fromSplit = full.split(" /from ");
            if (fromSplit.length < 2) {
                throw new NiviException("Haiya need to specify the start time with /from lah!");
            }
            String description = fromSplit[0].trim();
            String[] toSplit = fromSplit[1].split(" /to ");
            if (toSplit.length < 2) {
                throw new NiviException("Eiii need to specify the end time with /to lah!");
            }
            String from = toSplit[0].trim();
            String to = toSplit[1].trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new NiviException("Description, start time, and end time cannot be empty laa aiyooo!");
            }
            return new Event(description, from, to);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NiviException("Oii cannot laa wrong format!!! should be like this --->>> event <description> /from <start> /to <end>");
        }
    }
}