package LoggingFramework.Strategies.Formatter;

import LoggingFramework.Models.LogMessage;

public class SimpleTextFormatter implements LogMessageFormatter {
    @Override
    public String formatMessage(LogMessage logMessage) {
        return String.format("[%s] [%s] %s  %s", logMessage.getTimestamp(), logMessage.getLevel(), logMessage.getSource(), logMessage.getMessage());
    }
}
