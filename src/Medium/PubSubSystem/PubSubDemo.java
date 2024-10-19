package Medium.PubSubSystem;

public class PubSubDemo {
    public static void run(){
        // Create topics
        Topic topic1 = new Topic("Topic1");
        Topic topic2 = new Topic("Topic2");

        // Create publishers
        Publisher publisher1 = new Publisher("Publisher 1");
        Publisher publisher2 = new Publisher("Publisher 2");

        // Create subscribers
        Subscriber subscriber1 = new PrintSub("Subscriber1");
        Subscriber subscriber2 = new PrintSub("Subscriber2");
        Subscriber subscriber3 = new PrintSub("Subscriber3");

        publisher1.registerTopic(topic1);
        publisher2.registerTopic(topic2);

        // Subscribe to topics
        topic1.addSubscriber(subscriber1);
        topic1.addSubscriber(subscriber2);
        topic2.addSubscriber(subscriber2);
        topic2.addSubscriber(subscriber3);

        // Publish messages
        publisher1.publish(topic1, new Message("Message1 for Topic1"));
        publisher1.publish(topic1, new Message("Message2 for Topic1"));
        publisher2.publish(topic2, new Message("Message1 for Topic2"));


        // Unsubscribe from a topic
        topic1.removeSubscriber(subscriber2);

        // Publish more messages
        publisher1.publish(topic1, new Message("Message3 for Topic1"));
        publisher2.publish(topic2, new Message("Message2 for Topic2"));
    }
}

