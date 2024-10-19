package StackOverFlow;

import java.util.ArrayList;

public class User{
  private final Integer id;
  private final String name;
  private final String email;
  private Integer reputation;
  private final ArrayList<Question> questions;
  private final ArrayList<Answer> answers;
  private final ArrayList<Comment> comments;
  
  public User(Integer id,String name,String email){
    this.email = email;
    this.id = id;
    this.name = name;
    this.reputation = 0;
    questions = new ArrayList<Question>();
    answers = new ArrayList<Answer>();
    comments = new ArrayList<Comment>();
  }

  public synchronized void updateReputation(int value){
    reputation += value;
    if(reputation < 0){
      reputation = 0;
    }
  }

  public Question postQuestion(String title,String description,ArrayList<String> tags){
    Question question = new Question(this,title,description,tags);
    questions.add(question);
    updateReputation(5);
    return question;
  }

  public Answer postAnswer(String title,String description){
    Answer answer = new Answer(this,title,description);
    answers.add(answer);
    updateReputation(5);
    return answer;
  }

  public Comment addComment(Commentable commentable,String commentStr){
    Comment comment = new Comment(this,commentStr);
    comments.add(comment);
    commentable.addComment(this, comment);
    updateReputation(2);
    return comment;
  }


  // Getters and Setters
  
  public Integer getId() {
  	return id;
  }
  public String getName() {
  	return name;
  }
  public String getEmail() {
  	return email;
  }
  public Integer getReputation() {
  	return reputation;
  }
  public void setReputation(Integer reputation) {
  	this.reputation = reputation;
  }
  public ArrayList<Question> getQuestions() {
  	return questions;
  }
  public ArrayList<Answer> getAnswers() {
  	return answers;
  }
  public ArrayList<Comment> getComments() {
  	return comments;
  }

}