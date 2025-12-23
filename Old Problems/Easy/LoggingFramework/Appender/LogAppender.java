package LoggingFramework.Appender;

import LoggingFramework.LogMessage;

public interface LogAppender{
  void append(LogMessage logMessage);
}