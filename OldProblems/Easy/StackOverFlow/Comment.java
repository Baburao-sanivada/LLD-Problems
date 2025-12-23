package StackOverFlow;

public class Comment{
  private final User user;
  private final String comment;
  private final int id;
  public Comment(User user,String comment){
    this.user=user;
    id = generateId();
    this.comment=comment;
  }
  public User getUser(){
    return user;
  }
  public String getComment(){
    return comment;
  }
  private int generateId(){
    return (int)(System.currentTimeMillis()%Integer.MAX_VALUE);
  }
  public int getId(){
    return id;
  }
}