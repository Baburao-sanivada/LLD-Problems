package Easy.LoggingFramework.Appender;

import Easy.LoggingFramework.LogMessage;

public class FileAppender implements LogAppender{
  // Set File destination
  private String fileName;
  public FileAppender(String fileName){
    this.fileName = fileName;
  }

  public void append(LogMessage logMessage){
    // Append to file
    System.out.println(logMessage.toString()+" Message is appended to file");
  }
}