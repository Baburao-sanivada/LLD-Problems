package Easy.LoggingFramework;

import Easy.LoggingFramework.Appender.LogAppender;

public class LoggerConfig {
  private LogType logType;
  private LogAppender logAppender;
  public LoggerConfig(LogType logType,LogAppender logAppender){
    this.logType = logType;
    this.logAppender = logAppender;
  }
  public LogType getLogType() {
  	return logType;
  }
  public void setLogType(LogType logType) {
  	this.logType = logType;
  }
  public LogAppender getLogAppender() {
  	return logAppender;
  }
  public void setLogAppender(LogAppender logAppender) {
  	this.logAppender = logAppender;
  }
  public boolean isLoggable(LogType logType){
    return this.logType.equals(logType);
  }
}