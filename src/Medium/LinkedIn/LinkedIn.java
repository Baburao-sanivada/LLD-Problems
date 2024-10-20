package Medium.LinkedIn;

public class LinkedIn {
    private static LinkedIn instance;
    private final UserController userController;
    private final NotificationController notificationController;
    private final JobController jobController;
    private User currentLogedInUser;
    public static LinkedIn getInstance(UserController userController,NotificationController notificationController,JobController jobController){
        if(instance == null){
            instance = new LinkedIn(userController,notificationController,jobController);
        }
        return instance;
    }

    private LinkedIn(UserController userController, NotificationController notificationController, JobController jobController) {
        this.userController = userController;
        this.notificationController = notificationController;
        this.jobController = jobController;
    }

    public void sentMessage( User reciever, String description){
        if(currentLogedInUser == null){
            System.out.println("Please login/Register First");
            return;
        }
        User sender = currentLogedInUser;
        notificationController.notifyUserAboutMessage(sender,reciever,description);
    }

    public void postAJob(String title,String description,String requirement,String location){
        jobController.postAnewJob(title,description,requirement,location);
    }

    public void sendConnectionRequest(User reciever){
        if(currentLogedInUser == null){
            System.out.println("Please login/Register First");
            return;
        }
        User sender = currentLogedInUser;
        sender.connReqSent(reciever);
        reciever.connectionreqRecieved(sender);
    }

    public void subscribeToJobPosting(String title){
        if(currentLogedInUser == null){
            System.out.println("Please login/Register First");
            return;
        }
        currentLogedInUser.addJobsSubscriber(title);
    }

    public void acceptConnectionRequest(User user2){
        if(currentLogedInUser == null){
            System.out.println("Please login/Register First");
            return;
        }
        User user1 = currentLogedInUser;
        user2.connectionreqAccepted(user1);
        user1.connectionreqAccepted(user2);
    }

    public User login(String email,String password){
        User user = userController.login(email,password);
        if(user == null){
            System.out.println("Invalid Login Credentials");
        }
        else{
            System.out.println("Logged In Successfully");
            currentLogedInUser = user;
        }
        return user;
    }

    public User register(String name,String email,String password){
        User user = userController.registerUser(name,email,password);
        System.out.println("User Registration Successfull");
        return user;
    }

    public void logOut(){
        currentLogedInUser = null;
        System.out.println("Logged Out Successfully");
    }
}
