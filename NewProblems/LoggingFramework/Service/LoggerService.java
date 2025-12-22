package LoggingFramework.Service;

import LoggingFramework.Enums.LogLevel;
import LoggingFramework.LogManager;
import LoggingFramework.Models.LogMessage;
import LoggingFramework.Service.Interface.ILogger;

public class LoggerService implements ILogger {
    private String fileName;
    private LogManager logManager;

    public static LoggerService getLogger(String fileName) {
        return new LoggerService(fileName);
    }

    private LoggerService(String fileName) {
        this.fileName = fileName;
        this.logManager = LogManager.getInstance();
    }

    @Override
    public void info(String message) {
        logManager.addLogToDestination(getLogMessageObject(message, LogLevel.INFO));
    }

    @Override
    public void warning(String message) {
        logManager.addLogToDestination(getLogMessageObject(message, LogLevel.WARN));
    }

    @Override
    public void error(String message) {
        logManager.addLogToDestination(getLogMessageObject(message, LogLevel.ERROR));
    }

    @Override
    public void debug(String message) {
        logManager.addLogToDestination(getLogMessageObject(message, LogLevel.DEBUG));
    }

    public String getFileName() {
        return fileName;
    }

    public LogMessage getLogMessageObject(String message, LogLevel logLevel) {
        return new LogMessage(message, logLevel, fileName);
    }
}
