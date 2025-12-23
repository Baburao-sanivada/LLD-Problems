package LoggingFramework;


public class LogMessage{
  private LogType logType;
  private String message;
  private long time;
  public LogMessage(LogType logType,String message){
    this.logType = logType;
    this.message = message;
    this.time = System.currentTimeMillis();
    
  }
  
  public void setMessage(String message){
    this.message = message;
  }
  public void setLogType(LogType logType){
    this.logType = logType;
  }

  public String toString(){
    return "["+logType+"] "+time+" : "+message;
  }
}