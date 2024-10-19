package Medium.PubSubSystem;

public interface Subscriber {
    void onMessage(Message message);
}