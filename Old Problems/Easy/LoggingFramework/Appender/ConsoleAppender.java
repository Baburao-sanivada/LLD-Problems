package LoggingFramework.Appender;

import LoggingFramework.LogMessage;

public class ConsoleAppender implements LogAppender{
  // Set File destination
  public ConsoleAppender(){
  }

  public void append(LogMessage logMessage){
    // Append to file
    System.out.println(logMessage.toString());
  }
}