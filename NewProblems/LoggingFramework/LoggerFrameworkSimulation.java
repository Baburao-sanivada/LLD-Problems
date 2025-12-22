package LoggingFramework;

import LoggingFramework.Configuration.LogConfiguration;
import LoggingFramework.Enums.AppENV;
import LoggingFramework.Enums.LogAppenderType;
import LoggingFramework.Enums.LogLevel;
import LoggingFramework.Factory.LoggerFactory;
import LoggingFramework.Service.LoggerService;

public class LoggerFrameworkSimulation {
    public static void main(String[] args) {
        // configure the logging framework
        LogManager logManager = LogManager.getInstance();
        logManager.setLogLevel(LogLevel.INFO);
        logManager.enableLogAppender(LogAppenderType.CONSOLE);
        logManager.setLogFilePath("app.log");
        logManager.enableLogAppender(LogAppenderType.FILE);


        // Test few logs
        LoggerService logger = LoggerFactory.getLogger("MyApp");
        logger.info("This is an info message");
        logger.warning("This is a warning message");
        logger.error("This is an error message");
        logger.debug("This is a debug message");



        LoggerService logger2 = LoggerFactory.getLogger("ProductService");
        logger2.info("Product created successfully");
        logger2.warning("Low stock for product");
        logger2.error("Failed to update product");
        logger2.debug("Product details: {id: 1, name: 'Laptop'}");
    }


}
