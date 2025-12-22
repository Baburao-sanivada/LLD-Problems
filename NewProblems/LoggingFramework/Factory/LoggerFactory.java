package LoggingFramework.Factory;

import LoggingFramework.Service.LoggerService;

public class LoggerFactory {

    public static LoggerService getLogger(String source) {
        return LoggerService.getLogger(source);
    }
}
