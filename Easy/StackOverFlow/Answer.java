package StackOverFlow;

import java.util.ArrayList;
import java.util.Date;

public class Answer implements Votable,Commentable {
  private final User author;
  private final int id;
  private final String title;
  private final String description;
  private final ArrayList<Vote> votes;
  private final Date createdDate;
  private final ArrayList<Comment> comments;
  private boolean isAccepted;

  public Answer(User author,String title,String description){
    this.id = generateId();
    this.title = title;
    this.author = author;
    this.description = description;
    this.votes = new ArrayList<Vote>();
    this.createdDate = new Date();
    comments = new ArrayList<Comment>();
    isAccepted = false;
  }

  public void markAnswerAsAccepted(){
    isAccepted = true;
    author.updateReputation(15);
  }


  public void vote(User user,int value){
    if(value!=-1 && value!=1){
      throw new IllegalArgumentException("Invalid vote value");
    }
    for(Vote vote:votes){
      if(vote.getUser().equals(user)){
        votes.remove(vote);
        break;
      }
    }
    votes.add(new Vote(user,value));
    author.updateReputation(value*5);
  }

  public int getVoteCount(){
    int count=0;
    for(Vote vote:votes){
      count+=vote.getvalue();
    }
    return count;
  }

  public void addComment(User user,Comment comment){
    comments.add(comment);
  }

  public ArrayList<Comment> getComments(){
    return comments;
  }


  private int generateId() {
    return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
  }
  
  public User getAuthor() {
  	return author;
  }
  public int getId() {
  	return id;
  }
  public String getTitle() {
  	return title;
  }
  public String getDescription() {
  	return description;
  }
  public ArrayList<Vote> getVotes() {
  	return votes;
  }
  public Date getCreatedDate() {
  	return createdDate;
  }

  
}