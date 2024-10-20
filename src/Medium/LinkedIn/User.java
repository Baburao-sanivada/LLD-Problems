package Medium.LinkedIn;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class User {
    private final String userId;
    private String userName;
    private final String email;
    private String password;
    private final Profile profile;

    private final Map<String,User> connReqSent;
    private final Map<String,User> connReqRecieved;
    private final Map<String,User> connections;
    private final Map<String,Message> messagesSent;
    private final Map<String,Message> messagesRecived;
    private final List<String> jobsSubscribed;

    public User( String userName, String email, String password) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.email = email;
        this.password = password;
        profile = new Profile(userName);
        connections = new ConcurrentHashMap<>();
        connReqSent = new ConcurrentHashMap<>();
        connReqRecieved = new ConcurrentHashMap<>();
        jobsSubscribed = new CopyOnWriteArrayList<>();
        messagesSent = new ConcurrentHashMap<>();
        messagesRecived = new ConcurrentHashMap<>();

    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public Map<String, User> getConnReqSent() {
        return connReqSent;
    }

    public Map<String, User> getConnReqRecieved() {
        return connReqRecieved;
    }

    public Map<String, User> getConnections() {
        return connections;
    }

    public void addMessageSent(Message message){
        messagesSent.put(message.getId(),message);
    }

    public List<String> getJobsSubscribed() {
        return jobsSubscribed;
    }

    public void connectionreqRecieved(User user){
        System.out.println("Recieved Connection Req from :"+user.getUserName());
        connReqRecieved.put(user.getUserId(),user);
    }

    public void connectionreqAccepted(User user){
        System.out.println("Accepted Connection Req from :"+user.getUserName());
        connReqRecieved.remove(user.getUserId());
        connReqSent.remove(user.getUserId());
        connections.put(user.getUserId(),user);
    }

    public void connReqSent(User user){
        System.out.println("Sent Connection Req to :"+user.getUserName());
        connReqSent.put(user.getUserId(),user);
    }

    public void jobNotification(Job job){
        System.out.println("A new Job is posted :"+job.getJobTitle());
    }

    public void recivedAMessageNotification(Message message){
        System.out.println("Recived a message from :"+message.getSender().getUserName());
        System.out.println(message.getDescription());
        message.setSeen();
        messagesRecived.put(message.getId(),message);
    }

    public void messageSent(Message message){
        messagesSent.put(message.getId(),message);
    }

    public void addJobsSubscriber(String title){
        jobsSubscribed.add(title);
    }
    public void removeJobsSubscriber(String title){
        jobsSubscribed.remove(title);
    }


}
