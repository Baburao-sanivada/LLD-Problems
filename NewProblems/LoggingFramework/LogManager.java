package LoggingFramework;

import LoggingFramework.Configuration.LogConfiguration;
import LoggingFramework.Enums.LogAppenderType;
import LoggingFramework.Enums.LogLevel;
import LoggingFramework.Models.LogMessage;
import LoggingFramework.Strategies.Formatter.LogMessageFormatter;
import LoggingFramework.Strategies.Logging.DataBaseAppender;
import LoggingFramework.Strategies.Logging.FileAppender;
import LoggingFramework.Strategies.Logging.LogAppender;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LogManager {
    private LogConfiguration logConfiguration;
    private  static LogManager instance;
    private AsynLogProcessor asynLogProcessor;
    Map<LogAppenderType, LogAppender> logAppenders;
    Map<LogAppenderType,Boolean> isAppenderEnabled;

    public static LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }

    private LogManager() {
        this.logConfiguration = LogConfiguration.getInstance();
        this.asynLogProcessor = new AsynLogProcessor(3);
        this.logAppenders = new ConcurrentHashMap<>();
        logAppenders.put(LogAppenderType.CONSOLE, new LoggingFramework.Strategies.Logging.ConsoleAppender(asynLogProcessor));
        this.isAppenderEnabled = new ConcurrentHashMap<>();
    }

    public void addTask(Runnable logTask) {
        asynLogProcessor.submitLogTask(logTask);
    }

    public void shutdown() {
        asynLogProcessor.stop();
    }

    public void addLogToDestination(LogMessage logMessage) {
        if(!logMessage.getLevel().isGreaterOrEqual(logConfiguration.getLogLevel())) {
            return;
        }
        // format message first
        LogMessageFormatter logFormatter = logConfiguration.getLogMessageFormatter();

        // check appender
        String formattedMessage = logFormatter.formatMessage(logMessage);

        // call appender
        for (LogAppender logAppender : logAppenders.values()) {
            logAppender.writeLog(formattedMessage);
        }
    }

    public void setLogFilePath(String logFilePath) {
        logConfiguration.setLogFilePath(logFilePath);
        logAppenders.put(LogAppenderType.FILE, new FileAppender(logFilePath,asynLogProcessor));
    }

    public void setDbConnectionString(String dbConnectionString) {
        logConfiguration.setDbConnectionString(dbConnectionString);
         logAppenders.put(LogAppenderType.DATABASE, new DataBaseAppender(dbConnectionString,asynLogProcessor));
    }

    public void enableLogAppender(LogAppenderType logAppenderType) {
        isAppenderEnabled.put(logAppenderType, true);
    }

    public void setLogLevel(LogLevel logLevel) {
        logConfiguration.setLogLevel(logLevel);
    }

}
