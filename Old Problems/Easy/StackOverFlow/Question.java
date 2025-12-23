package StackOverFlow;

import java.util.ArrayList;
import java.util.Date;

public class Question implements Votable,Commentable {
  private final User author;
  private final int id;
  private final String title;
  private final String description;
  private final ArrayList<Vote> votes;
  private final Date createdDate;
  private final ArrayList<Comment> comments;
  private final ArrayList<Tag> tags;
  private final ArrayList<Answer> answers;

  public Question(User author,String title,String description,ArrayList<String> inputTags){
    this.id = generateId();
    this.title = title;
    this.author = author;
    this.description = description;
    this.votes = new ArrayList<Vote>();
    this.createdDate = new Date();
    this.comments = new ArrayList<Comment>();
    this.answers = new ArrayList<Answer>();
    this.tags = new ArrayList<Tag>();
    for(String tag:inputTags){
      tags.add(new Tag(tag));
    }
  }

  public void addAnswer(Answer answer){
    answers.add(answer);
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
  
  public ArrayList<Tag> getTags() {
  	return tags;
  }
  
  public ArrayList<Answer> getAnswers() {
  	return answers;
  }
  
}