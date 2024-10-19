package TaskManagementSystem;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class TaskManager{
  private static TaskManager instance;
  private final ConcurrentHashMap<String,Task> tasks;
  private final ConcurrentHashMap<String,ArrayList<Task>> userTasks;

  public static synchronized TaskManager getInstance(){
    if(instance==null){
      instance = new TaskManager();
    }
    return instance;
  }

  private TaskManager(){
    tasks = new ConcurrentHashMap<String,Task>();
    userTasks = new ConcurrentHashMap<String,ArrayList<Task>>();
  }

  public void createTask(Task task){
    tasks.put(task.getId(),task);
    assignTaskToUser(task.getAssignee().getId(),task);
  }

  

  public void updateTask(Task task){
    Task taskPresent = tasks.get(task.getId());
    if(taskPresent!=null){
      synchronized (taskPresent){
        taskPresent.setTitle(task.getTitle());
        taskPresent.setDescription(task.getDescription());
        taskPresent.setPriority(task.getPriority());
        taskPresent.setDueDate(task.getDueDate());
        taskPresent.setStatus(task.getStatus());
        // check if new User
        User newUser = task.getAssignee();
        if(taskPresent.getAssignee().getId() != newUser.getId()){
          unAssignTaskFromUser(taskPresent.getAssignee().getId(),taskPresent);
          assignTaskToUser(newUser.getId(),taskPresent);
        }
      }
    }
  }

  public ArrayList<Task> filterTaskWithText(String txt){
    ArrayList<Task> filteredTasks = new ArrayList<Task>();
    for(Task task: tasks.values()){
      if(task.getTitle().contains(txt) || task.getDescription().contains(txt)){
        filteredTasks.add(task);
      }
    }
    return filteredTasks;
  }

  public void deleteTask(String taskId){
    Task task = tasks.get(taskId);
    tasks.remove(taskId);
    if(task != null){
      unAssignTaskFromUser(task.getAssignee().getId(), task);
    }
  }


  public void changeTaskStatus(String taskId,Status newStatus){
    Task task = tasks.get(taskId);
    if(task!=null){
      task.setStatus(newStatus);
    }
  }

  public ArrayList<Task> getHistory(User user){
    return userTasks.getOrDefault(user.getId(),new ArrayList<Task>());
  }

  public void assignTaskToUser(String userId,Task task){
    if(!userTasks.containsKey(userId)){
      userTasks.put(userId,new ArrayList<Task>());
    }
    userTasks.get(userId).add(task);
  }

  public void unAssignTaskFromUser(String userId,Task task){
    ArrayList<Task> userSpecificTasks = userTasks.get(userId);
    for(Task userTask: userSpecificTasks){
      if(userTask.getId() == task.getId()){
        userSpecificTasks.remove(userTask);
        break;
      }
      
    }
  }
}