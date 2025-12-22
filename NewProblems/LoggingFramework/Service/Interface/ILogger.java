package LoggingFramework.Service.Interface;

public interface ILogger {
    void info(String message);
    void warning(String message);
    void error(String message);
    void debug(String message);
}
