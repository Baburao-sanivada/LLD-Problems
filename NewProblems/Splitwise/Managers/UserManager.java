package Splitwise.Managers;

import Splitwise.Models.User;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    Map<String,User> users;
    private static UserManager instance = null;

    public static UserManager getInstance(){
        if(instance == null){
            synchronized (UserManager.class){
                if(instance == null){
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    private UserManager(){
        users = new ConcurrentHashMap<>();
    }

    public void addUser(User user){
        users.put(user.getUserId(),user);
    }

    public Optional<User> getUserById(String userId){
        return Optional.ofNullable(users.get(userId));
    }
}
