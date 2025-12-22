package LoggingFramework.Models;

import LoggingFramework.Enums.LogLevel;

import java.time.LocalDateTime;

public class LogMessage {
    private String message;
    private LogLevel level;
    private LocalDateTime timestamp;
    private String source;
    public LogMessage(String message, LogLevel level, String source) {
        this.message = message;
        this.level = level;
        this.timestamp = LocalDateTime.now();
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
