package Easy.LoggingFramework;

import Easy.LoggingFramework.Appender.ConsoleAppender;
import Easy.LoggingFramework.Appender.LogAppender;

public class Logger {
  private static Logger instance;

  private LoggerConfig loggerConfig;

  public static synchronized Logger getInstance(){
    if(instance == null){
      instance = new Logger(new LoggerConfig(LogType.INFO,new ConsoleAppender()));
    }
    return instance;
  }

  private Logger(LoggerConfig loggerConfig){
    this.loggerConfig = loggerConfig;
  }

  public void log(LogType logType,String message){
    LogMessage logMessage = new LogMessage(logType,message);
    if(this.loggerConfig.isLoggable(logType)){
      loggerConfig.getLogAppender().append(logMessage);
    }
  }

  public void debug(String message){
    log(LogType.DEBUG,message);
  }

  public void info(String message){
    log(LogType.INFO,message);
  }
  public void warn(String message){
    log(LogType.WARN,message);
  }
  public void error(String message){
    log(LogType.ERROR,message);
  }


  public void setLoggerConfig(LoggerConfig loggerConfig){
    this.loggerConfig = loggerConfig;
  }

  
}