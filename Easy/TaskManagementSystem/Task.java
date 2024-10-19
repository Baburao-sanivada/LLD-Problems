package TaskManagementSystem;

import java.util.Date;

public class Task{
  private String id;
  private String title;
  private String description;
  private Status status;
  private Priority priority;
  private Date dueDate;
  private User assignee;
  public Task(String id,String title,String description,Priority priority,Date date,User assignee){
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = Status.Pending;
    this.priority = priority;
    this.assignee = assignee;
    this.dueDate = date;
  }
  public String getId() {
  	return id;
  }
  public void setId(String id) {
  	this.id = id;
  }
  public String getTitle() {
  	return title;
  }
  public void setTitle(String title) {
  	this.title = title;
  }
  public String getDescription() {
  	return description;
  }
  public void setDescription(String description) {
  	this.description = description;
  }
  public Status getStatus() {
  	return status;
  }
  public void setStatus(Status status) {
  	this.status = status;
  }
  public Priority getPriority() {
  	return priority;
  }
  public void setPriority(Priority priority) {
  	this.priority = priority;
  }
  public User getAssignee() {
  	return assignee;
  }
  public void setAssignee(User assignee) {
  	this.assignee = assignee;
  }
  public Date getDueDate() {
  	return dueDate;
  }
  public void setDueDate(Date dueDate) {
  	this.dueDate = dueDate;
  }
}