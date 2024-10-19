package Easy.LoggingFramework.Appender;

import Easy.LoggingFramework.LogMessage;

public interface LogAppender{
  void append(LogMessage logMessage);
}