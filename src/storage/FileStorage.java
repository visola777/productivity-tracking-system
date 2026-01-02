package storage;

import java.io.*;
import task.Task;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {

    private static final String TASK_FILE = "data/tasks.dat";

    public void saveTasks(List<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASK_FILE))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TASK_FILE))) {
            tasks = (List<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            // file yo‘q bo‘lsa bo‘sh list qaytar
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
