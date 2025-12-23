package OldProblems.Medium.PubSubSystem;

public class PrintSub implements Subscriber{
    private final String name;

    public PrintSub(String name){
        this.name = name;
    }

    @Override
    public void onMessage(Message message){
        System.out.println("Recieved the Message: "+message.getContent());
    }
}