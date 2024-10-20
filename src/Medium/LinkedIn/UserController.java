package Medium.LinkedIn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserController {

    private final Map<String,User> users;
    private static UserController instance;

    public static UserController getInstance(){
        if(instance==null){
            instance = new UserController();
        }
        return instance;
    }

    public UserController(){
        users = new ConcurrentHashMap<>();
    }

    public User registerUser(String name,String emailId,String password){
        User user = new User(name,emailId,password);
        users.put(user.getUserId(),user);
        return user;
    }

    public User login(String email,String password){
        for(User user: users.values()){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
