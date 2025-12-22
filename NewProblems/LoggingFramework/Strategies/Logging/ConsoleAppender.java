package LoggingFramework.Strategies.Logging;

import LoggingFramework.AsynLogProcessor;

public class ConsoleAppender implements LogAppender {
    private AsynLogProcessor asynLogProcessor;

    public ConsoleAppender(AsynLogProcessor asynLogProcessor) {
        this.asynLogProcessor = asynLogProcessor;
    }

    @Override
    public void writeLog(String formattedMessage) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println(formattedMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        asynLogProcessor.submitLogTask(runnable);
    }
}
