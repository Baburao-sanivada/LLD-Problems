Logging Frame work

Requirements
- Multiple Log Levels
- Multiple Output Destinations (Console, File, Date base)
- Configurable Formating
- Thread Safety - Multple Threads can log simultanously

Entities Required
- Log Level Enum - DEBUG, INFO, WARN, ERROR - Priority Values
- LogDestination Enum - CONSOLE, FILE, DATABASE
- LogMessage - TIMESTAMP, DEBUG LEVEL, MESSAGE, Class Name
- MessageFormat - BOLD, ITALIC, SIMPLE
- Log Formatter - TimeStamp Format, Message Format, Prefer colur , ClassName Req
- ApplicationEnvironment Enum- DEV, TEST, PROD

Visualize flow
User - Configure Logg Level  - Configure Output destination -  Configure Format
User -> Logger Factory (Get Logger with class Name) -> Logger.info() -> Write to destination

------- Class Structure ---------

LoggerConfiguration - Singleton - getInstance()
    - SetLogLevel(int priority)
    - addOutputDestination(LogDestination dest, String path)
    - setMessageFormat(MessageFormat format)
    - setEnvironment(ApplicationEnvironment env)

LoggerFactory - generate Logger instance
    - getLogger(String className)

LogWriter - Interface
    - write(LogMessage message)

ConsoleLogWriter - implements LogWriter

FileLogWriter - implements LogWriter
    - File Path
    - write(LogMessage message)

DatabaseLogWriter - implements LogWriter
    - DB Connection String
    - write(LogMessage message)

Logger - Main Logging Class
    - info(String message)
    - debug(String message)
    - warn(String message)
    - error(String message)