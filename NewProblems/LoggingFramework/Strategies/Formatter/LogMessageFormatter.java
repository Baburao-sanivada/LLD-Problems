package LoggingFramework.Strategies.Formatter;

import LoggingFramework.Models.LogMessage;

public interface LogMessageFormatter {
    public String formatMessage(LogMessage logMessage);
}
