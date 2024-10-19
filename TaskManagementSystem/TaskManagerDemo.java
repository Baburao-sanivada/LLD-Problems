package TaskManagementSystem;

import java.util.ArrayList;
import java.util.Date;

public class TaskManagerDemo {
  
  public static void run(){
    TaskManager taskManager = TaskManager.getInstance();

    User user1 = new User("1","Babu","Babu@gmail.com");
    User user2 = new User("2","Kiran","kiran@gmail.com");
    User user3 = new User("3","Vinay","vinay@gmail.com");

    Task task1 = new Task("1","Task 1","Task 1 Description",Priority.HIGH,new Date(),user1);
    Task task2 = new Task("2","Task 2","Task 2 Description",Priority.MEDIUM,new Date(),user2);
    Task task3 = new Task("3","Task 3","Task 3 Description",Priority.LOW,new Date(),user3);

    taskManager.createTask(task1);
    taskManager.createTask(task2);
    taskManager.createTask(task3);

    task2.setDescription("Updated Description");
    taskManager.updateTask(task2);

    // Delete a task
    taskManager.deleteTask("3");

    // Get task history for a user
    ArrayList<Task> taskHistory = taskManager.getHistory(user1);
    System.out.println("Task History for " + user1.getName() + ":");
    for (Task task : taskHistory) {
        System.out.println(task.getTitle());
    }

  
    // Search tasks
    ArrayList<Task> searchResults = taskManager.filterTaskWithText("Task");
    System.out.println("Search Results:");
    for (Task task : searchResults) {
        System.out.println(task.getTitle());
    }

  }
}