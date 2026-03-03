package task;

/**
 * Make a  Event data type that extends Task, with attributes "from" and "to" as the start and finish time of the event.
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Constructor, initializes parameters description, from and to.       
     * @param description the description of the event.   
     * @param from the event start time.
     * @param to the event finish time.
     */
    public Event(String description, String from, String to ){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Override the toString method to return a full String description of the event task to be printed.
     * @return constructed String of description, start time, and finish time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: "+to+ ")";
    }

    /**
     * Override the toFileFormat method to return a full String description of the event task in the format that will be stored in the data file.
     * @return constructed String of description, start time, and finish time to be stored in the data file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "X" : "0") + " | " + description + " | " + from + " | " + to;
    }
}