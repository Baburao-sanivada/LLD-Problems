package LoggingFramework.Strategies.Logging;

import LoggingFramework.AsynLogProcessor;

public class DataBaseAppender implements LogAppender {
    private String connectionString;
    private AsynLogProcessor asynLogProcessor;
    @Override
    public void writeLog(String formattedMessage) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Written to database: connection string \n" + formattedMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        asynLogProcessor.submitLogTask(runnable);
    }

    public DataBaseAppender(String connectionString, AsynLogProcessor asynLogProcessor) {
        this.connectionString = connectionString;
        this.asynLogProcessor = asynLogProcessor;
    }
}
