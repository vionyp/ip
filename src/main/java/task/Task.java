package task;

/**
 * Make a Task data type with description and isDone to represent a task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor, initializes parameters description and isDone. isDone is initialized to false since it is undone when it was first created.     
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * getter method for the task description.
     * @return the task description.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * getter method for the task isDone status.
     * @return false if the task is not done yet, and otherwise.
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * Mark the task as done or undone by changing the isDone status.
     */
    public void markAsDone(){
        this.isDone = true;
    }
    public void markAsUndone(){
        this.isDone = false;
    }

    /**
     * getter method for the status icon of the task to symbolize whether the task is done or not.
     * @return "X" if the task is done and space if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Override the toString method to return a string representation of the task to be printed.
     * @return constructed String of status icon and description.   
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Override the toFileFormat method to return a string representation of the task in the format that will be stored in the data file.
     * @return constructed String of status icon and description to be stored in the data file.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}