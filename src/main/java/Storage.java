import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Strogage class load and save the task list to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor, initialize the Storage object with a file path.
     * @param filePath path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the task list from internal file and return it as an ArrayList of Task objects. If the file does not exist, a FileNotFoundException will be thrown.
     * @return an ArrayList of loaded Task objects from file.
     * @throws FileNotFoundException when the specified file path does not exist.
     * @throws IOException when the file is error or inaccesible.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        ArrayList<Task> loadedTasks = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = parseLineToTask(line); 
            if (task != null) {
                loadedTasks.add(task);
            }
        }
        scanner.close();
        return loadedTasks;
    }

    /**
     * Save the task list to the internal file. If the file does not exist yet, it will be created.
     * @throws IOException when the file being inaccessible or the disk being full.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File f = new File(filePath);
        
        if (f.getParentFile() != null && !f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }

        FileWriter fw = new FileWriter(f);
        for (Task task : tasks) {
            fw.write(task.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }
    
    private Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }
}