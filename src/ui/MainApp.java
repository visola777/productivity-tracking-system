package ui;

import auth.AuthService;
import auth.User;
import task.Task;
import task.TaskService;
import storage.FileStorage;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        TaskService taskService = new TaskService();
        FileStorage storage = new FileStorage();
        Scanner sc = new Scanner(System.in);

        // oldingi tasklarni load qilamiz
        List<Task> tasks = storage.loadTasks();
        tasks.forEach(taskService::addTask);

        System.out.println("Welcome to Productivity Tracking System");

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();
                boolean success = authService.register(new User(username, password));
                if (success) System.out.println("Registered successfully");
                else System.out.println("Username already exists");

            } else if (choice.equals("2")) {
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();
                boolean login = authService.login(username, password);
                if (login) {
                    System.out.println("Login successful!");

                    while (true) {
                        System.out.println("\n1. Add Task\n2. List Tasks\n3. Logout");
                        String taskChoice = sc.nextLine();
                        if (taskChoice.equals("1")) {
                            System.out.print("Task Title: ");
                            String title = sc.nextLine();
                            System.out.print("Task Description: ");
                            String desc = sc.nextLine();
                            taskService.addTask(new Task(title, desc));
                            storage.saveTasks(taskService.getTasks());
                            System.out.println("Task added!");

                        } else if (taskChoice.equals("2")) {
                            List<Task> currentTasks = taskService.getTasks();
                            if (currentTasks.isEmpty()) System.out.println("No tasks found");
                            else currentTasks.forEach(t -> 
                                System.out.println("- " + t.getTitle() + " [" + (t.isCompleted() ? "Completed" : "Pending") + "]")
                            );
                        } else if (taskChoice.equals("3")) break;
                    }

                } else System.out.println("Login failed!");

            } else if (choice.equals("3")) break;
        }

        System.out.println("Goodbye!");
    }
}
