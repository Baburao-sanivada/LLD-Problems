package LoggingFramework.Configuration;

import LoggingFramework.Enums.AppENV;
import LoggingFramework.Enums.LogLevel;
import LoggingFramework.Strategies.Formatter.LogMessageFormatter;
import LoggingFramework.Strategies.Formatter.SimpleTextFormatter;

public class LogConfiguration {
    private static LogConfiguration instance;
    private AppENV appENV;
    private LogLevel logLevel;
    private String logFilePath = "";
    private String dbConnectionString = "";
    private LogMessageFormatter logMessageFormatter;

    public synchronized static LogConfiguration getInstance() {
        if (instance == null) {
            instance = new LogConfiguration();
        }
        return instance;
    }

    private LogConfiguration() {
        this.appENV = AppENV.DEVELOPMENT;
        this.logLevel = LogLevel.DEBUG;
        this.logMessageFormatter = new SimpleTextFormatter(); // default formatter can be set here
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void setDbConnectionString(String dbConnectionString) {
        this.dbConnectionString = dbConnectionString;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public String getDbConnectionString() {
        return dbConnectionString;
    }

    public void setAppENV(AppENV appENV) {
        this.appENV = appENV;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public AppENV getAppENV() {
        return appENV;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogMessageFormatter(LogMessageFormatter logMessageFormatter) {
        this.logMessageFormatter = logMessageFormatter;
    }

    public LogMessageFormatter getLogMessageFormatter() {
        return logMessageFormatter;
    }

}
