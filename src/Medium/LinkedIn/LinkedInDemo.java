package Medium.LinkedIn;

public class LinkedInDemo {
    public static void run() {
        UserController userController = UserController.getInstance();
        NotificationController notificationController = new NotificationController();
        JobController jobController = JobController.getInstance(notificationController);

        LinkedIn linkedIn = LinkedIn.getInstance(userController,notificationController,jobController);

        User user1 = linkedIn.register("Babu","Babu@gmail.com","1234");
        User user2 = linkedIn.register("Vinay","Vinay@gmail.com","1234");
        User user3 = linkedIn.register("HK","HK@gmail.com","1234");
        User user4 = linkedIn.register("Kiran","Kiran@gmail.com","1234");

        linkedIn.login("Babu@gmail.com","1234");
        linkedIn.subscribeToJobPosting("Software Developer");

        linkedIn.postAJob("Software Developer 2","Des","Req","HYD");
        linkedIn.postAJob("Software Developer","Des","Req","BNG");

        linkedIn.sendConnectionRequest(user2);
        linkedIn.sentMessage(user3,"Hi HK, How you dng");

        linkedIn.logOut();
        linkedIn.login("Vinay@gmail.com","1234");
        linkedIn.acceptConnectionRequest(user1);


    }
}
