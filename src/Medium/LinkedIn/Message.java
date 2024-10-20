package Medium.LinkedIn;

import java.util.UUID;

public class Message {
    private final String id;
    private final String description;
    private final User sender;
    private final User reciver;
    private boolean isSeen;

    public Message(String description, User sender, User reciver) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.sender = sender;
        this.reciver = reciver;
        this.isSeen = false;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public User getSender() {
        return sender;
    }

    public User getReciver() {
        return reciver;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen() {
        isSeen = true;
    }
}
