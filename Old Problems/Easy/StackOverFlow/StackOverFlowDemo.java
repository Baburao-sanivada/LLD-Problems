package StackOverFlow;

import java.util.ArrayList;
import java.util.Arrays;

public class StackOverFlowDemo{
  public StackOverFlowDemo(){
    StackOverFlow stackOverFlow = StackOverFlow.getInstance();
    User user1 = new User(1,"Babu","plsgq@example.com");
    User user2 = new User(2,"Kiran","mynbi@example.com");
    User user3 = new User(3,"Adil","mynbi@example.com");
    User user4 = new User(4,"Balu","mynbi@example.com");

    // Add USers
    stackOverFlow.addUser(user1);
    stackOverFlow.addUser(user2);
    stackOverFlow.addUser(user3);
    stackOverFlow.addUser(user4);


    // Ask Question
    Question q1 = stackOverFlow.askQuestion(user1, "java DSA", "LinkedList", new ArrayList<String>(Arrays.asList("LinekdList","DSA")));


    // Question q2 = stackOverFlow.askQuestion(user2, "Java DSA", "Stacks & Queue", new ArrayList<String>(Arrays.asList("LinekdList","DSA")));



    // Answer Question
    Answer ans1 = stackOverFlow.answerQuestion(user2,q1, "Answer1", "Answer1 Desc");

    stackOverFlow.addComment(user3, ans1, "I think the answer is correct");
    stackOverFlow.addComment(user4, ans1, "coment2");

    stackOverFlow.addComment(user3, q1, "Good Question");

    stackOverFlow.vote(user2,q1,1);

    stackOverFlow.vote(user3,ans1,1);

    System.out.println(user1.getReputation());
    System.out.println(user2.getReputation());
    System.out.println(user3.getReputation());
    System.out.println(user4.getReputation());


    // Demonstrate search functionality
    System.out.println("\nSearch Results for 'java':");
    ArrayList<Question> searchResults = stackOverFlow.filterByText("java");
    for (Question q : searchResults) {
        System.out.println(q.getTitle());
    }
    
    
  }
}