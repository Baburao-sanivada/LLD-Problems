package Easy.LoggingFramework.Appender;

import Easy.LoggingFramework.LogMessage;

public class ConsoleAppender implements LogAppender {
  // Set File destination
  public ConsoleAppender(){
  }

  public void append(LogMessage logMessage){
    // Append to file
    System.out.println(logMessage.toString());
  }
}