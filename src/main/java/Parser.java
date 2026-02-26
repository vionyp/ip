import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.time.format.DateTimeParseException;

public class Parser {

    public static Task parseTodo(String userInput) throws NiviException {
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            throw new NiviException("The description of the todo still empty whatt!??!?!?!");
        }
        return new Todo(description);
    }

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