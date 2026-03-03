package task;

/**
 * Make a Todo data type that extends Task, to represent a todo task in the task list.
 */
public class Todo extends Task{

    /**
     * Constructor, initializes parameter description. isDone is initialized to false since it is undone when it was first created.     
     * @param description   the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /** 
     * Override the toString method to return a full String description of the todo task to be printed.
     * @return constructed String of description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
    
    /**
     * Override the toFileFormat method to return a full String description of the todo task in the format that will be stored in the data file.
     * @return constructed String of description to be stored in the data file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "X" : "0") + " | " + description;
    }
}