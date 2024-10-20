package Medium.LinkedIn;

import java.util.List;
import java.util.Map;

public class NotificationController {

    public void NotifyUserAboutTheNewJobPosting(Job job, Map<String, List<User>> jobSubscribers){
        for(String key: jobSubscribers.keySet()){
            if(job.getJobTitle().contains(key)){
                for(User user: jobSubscribers.get(key)){
                    user.jobNotification(job);
                }
            }
        }
    }

    public void notifyUserAboutMessage(User sender, User reciever, String description){
        Message message = new Message(description,sender,reciever);
        message.getReciver().recivedAMessageNotification(message);
        message.getSender().messageSent(message);
    }

    public void notifyUserAboutConnectionRequest(User sender,User reciever){
        sender.connReqSent(reciever);
        reciever.connectionreqRecieved(sender);
    }
}
