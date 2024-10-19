package StackOverFlow;

import java.util.ArrayList;
import java.util.HashMap;

public class StackOverFlow{
  private final HashMap<Integer,User> users;
  private final HashMap<Integer,Question> questions;
  private final HashMap<Integer,Answer> answers;
  private final HashMap<Integer,Comment> comments;
  private final HashMap<Integer,Vote> votes;
  private static StackOverFlow instance;

  public synchronized static StackOverFlow getInstance(){
    if(instance==null) instance = new StackOverFlow();
    return instance;
  }
  
  private StackOverFlow(){
    this.users = new HashMap<Integer,User>();
    this.questions = new HashMap<Integer,Question>();
    this.answers = new HashMap<Integer,Answer>();
    this.comments = new HashMap<Integer,Comment>();
    this.votes = new HashMap<Integer,Vote>();
  }

  public void addUser(User user){
    int id = users.size()+1;
    users.put(id,user);
  }

  public Question askQuestion(User user,String title,String description,ArrayList<String> tags){
    Question question = user.postQuestion(title, description, tags);
    questions.put(question.getId(),question);
    return question;
  }

  public Answer answerQuestion(User user,Question question,String title,String description){
    Answer answer = user.postAnswer(title, description);
    question.addAnswer(answer);
    answers.put(answer.getId(),answer);
    return answer;
  }

  public Comment addComment(User user,Commentable commenatable,String commentStr){
    Comment comment = user.addComment(commenatable,commentStr);
    comments.put(comment.getId(),comment);
    return comment;
  }

  public void vote(User user,Votable votable,int value){
    votable.vote(user,value);
    Vote vote = new Vote(user,value);
    votes.put(votes.size()+1,vote);
  }

  public void markAnswerAsAccepted(Answer answer){
    answer.markAnswerAsAccepted();
  }

  public ArrayList<Question> getQuestionsByUser(User user){
    return user.getQuestions();
  }

  public ArrayList<Question> filterByText(String txt){
    ArrayList<Question> filtered = new ArrayList<Question>();
    for(Question question: questions.values()){
      if(question.getTitle().contains(txt) || question.getDescription().contains(txt)){
        filtered.add(question);
      }
    }
    return filtered;
  }

  public HashMap<Integer, User> getUsers() {
  	return users;
  }
  
  public HashMap<Integer, Question> getQuestions() {
  	return questions;
  }
  
  public HashMap<Integer, Answer> getAnswers() {
  	return answers;
  }
  
  public HashMap<Integer, Comment> getComments() {
  	return comments;
  }
  
  public HashMap<Integer, Vote> getVotes() {
  	return votes;
  }

  
}