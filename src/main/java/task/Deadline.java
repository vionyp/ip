package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Make a Deadline data type that extends Task, with attribute "by" as the deadline date.
 */
public class Deadline extends Task{
    protected LocalDate by;

    /**
     * Constructor, initializes parameters description and by.       
     * @param description the description of the deadline task.
     * @param by the deadline date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Override the toString method to return a full String description of the deadline task to be printed.
     * @return constructed String of description and deadline date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Override the toFileFormat method to return a full String description of the deadline task in the format that will be stored in the data file.
     * @return constructed String of description and deadline date to be stored in the data file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "X" : "0") + " | " + description + " | " + by;
    }
}