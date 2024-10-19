package LoggingFramework.Appender;

import LoggingFramework.LogMessage;

public class DataBaseAppender implements LogAppender{
  // Set File destination
  private String dbUrl;
  public DataBaseAppender(String dbUrl){
    this.dbUrl = dbUrl;
  }

  public void append(LogMessage logMessage){
    // Append to file
    System.out.println(logMessage.toString()+" Message is appended to Data Base");
  }
}