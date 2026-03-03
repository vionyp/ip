import task.Task;

import java.util.ArrayList;

/**
 * TaskList class manage the list of tasks in the program, such as, adding, removing, and retrieving tasks.
 */
public class TaskList{
    private ArrayList<Task> tasks;

    /**
     * default constructor, initialize the task list with a existing list of tasks.
     * @param tasks initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }   

    /**
     * overloaded constructor, initialize the task list with a empty list of tasks.
     */
    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    /**
     * getter method for list of tasks in TaskList object.
     * @return the list of tasks in the TaskList object.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }   

    /**
     * getter method for the number of tasks in TaskList object.
     * @return the number of tasks in the TaskList object.
     */
    public int size(){
        return tasks.size();
    }

    /**
     * Check if the task list is empty.
     * @return false if there is more than one 0 task in the task list, true if there is no task in the task list.
     */
    public boolean isEmpty(){
        return tasks.isEmpty();
    }

    /**
     * getter method for a task number "index" in the task list.
     * @param index the index of the task in the task list.
     * @return the task at the specified index in the task list.
     */
    public Task get(int index){
        return tasks.get(index);
    }

    /**
     * Add a task to the task list.
     * @param task the task that will be added to the task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove a task from the task list and return the task that has been removed.
     * @param index the index of the task that will be removed.
     * @return the task that was removed from the task list.
     */
    public Task remove(int index){
        return tasks.remove(index);
    }

    /**
     * Find and return a list of tasks that contain the keyword string on the task description.
     * @param keyword String that will be searched for in the task descriptions.
     * @return a list of tasks that contain the keyword in their description.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}