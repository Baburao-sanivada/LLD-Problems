package StackOverFlow;

public class Tag{
  private final int id;
  private final String tagName;
  public Tag(String tagName){
    id = generateId();
    this.tagName = tagName;
  }
  public int getId(){
    return id;
  }
  public String getTagName(){
    return tagName;
  }
  private int generateId(){
    return (int)(System.currentTimeMillis()%Integer.MAX_VALUE);
  }
}