

public class Topic {
    private final String name;
    private final Set<Subscriber> subscriberSet;

    public Topic(String name){
        this.name = name;
        subscriberSet = new CopyOnWriteArraySet<>();
    }

    public Set<Subscriber> getSubscriberSet() {
        return subscriberSet;
    }

    public String getName() {
        return name;
    }

    public void addSubscriber(Subscriber subscriber){
        subscriberSet.add(subscriber);
    }
    public void removeSubscriber(Subscriber subscriber){
        subscriberSet.remove(subscriber);
    }

    public void publish(Message message){
        for(Subscriber subscriber: subscriberSet){
            subscriber.onMessage(message);
        }
    }
}