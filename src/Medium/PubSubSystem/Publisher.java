package Medium.PubSubSystem;


import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<Topic> topics;
    private final String name;
    public Publisher(String name){
        this.name = name;
        topics = new ArrayList<>();
    }

    public void registerTopic(Topic topic){
        topics.add(topic);
    }

    public void removeTopic(Topic topic){
        topics.remove(topic);
    }

    public void publish(Topic topic,Message msg){
        boolean flag=true;
        for(Topic topicPresent: topics){
            if(topic.getName().equals(topicPresent.getName())){
                flag=false;
                topic.publish(msg);
                break;
            }
        }

        if(flag){
            System.out.println("Publisher: "+name+" cannot publish to this topic :"+topic.getName());
        }
    }
}