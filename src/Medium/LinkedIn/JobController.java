package Medium.LinkedIn;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class JobController {
    private static JobController instance;
    private final Map<String,Job> jobs;
    private final Map<String, List<User>> jobSubscribers;
    private final NotificationController notificationController;
    public static JobController getInstance(NotificationController notificationContoller){
        if(instance == null){
            instance = new JobController(notificationContoller);
        }
        return instance;
    }
    private JobController(NotificationController notificationContoller){
        jobs = new ConcurrentHashMap<>();
        jobSubscribers = new ConcurrentHashMap<>();
        this.notificationController = notificationContoller;
    }

    public synchronized void removeJobPosting(Job job){
        jobs.remove(job.getJobId());
    }

    public synchronized void postAnewJob(String title,String description,String requirement,String location){
        Job job = new Job(title,description,requirement,location);
        jobs.put(job.getJobId(),job);
        notificationController.NotifyUserAboutTheNewJobPosting(job,jobSubscribers);
    }

    public synchronized void addSubscriber(String title,User user){
        if(!jobSubscribers.containsKey(title)) jobSubscribers.put(title,new CopyOnWriteArrayList<>());
        jobSubscribers.get(title).add(user);
    }

}
