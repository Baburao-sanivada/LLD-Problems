package StackOverFlow;

import java.util.ArrayList;

public interface Commentable {
  void addComment(User user,Comment comment);
  ArrayList<Comment> getComments();
}