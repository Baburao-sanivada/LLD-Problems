package LoggingFramework.Enums;

public enum LogLevel {
    DEBUG(1),
    INFO(2),
    ERROR(3),
    WARN(4);
    private final int level;
    LogLevel(int i) {
        this.level = i;
    }

    public int getLevel() {
        return level;
    }

    public boolean isGreaterOrEqual(LogLevel other) {
        return this.level >= other.level;
    }
}
