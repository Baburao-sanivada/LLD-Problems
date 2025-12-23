package LoggingFramework;

import LoggingFramework.Appender.FileAppender;

public class LoggerDemo {
    public static void run() {
        Logger logger = Logger.getInstance();

        // Logging with default configuration
        logger.info("This is an information message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");

        // Changing log level and appender
        LoggerConfig config = new LoggerConfig(LogType.DEBUG, new FileAppender("app.log"));
        logger.setLoggerConfig(config);

        logger.debug("This is a debug message");
        logger.info("This is an information message");
    }
}

