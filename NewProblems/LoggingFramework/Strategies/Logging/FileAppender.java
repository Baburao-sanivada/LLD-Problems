package LoggingFramework.Strategies.Logging;

import LoggingFramework.AsynLogProcessor;

public class FileAppender implements LogAppender {
    private String filePath;
    private AsynLogProcessor asynLogProcessor;
    @Override
    public void writeLog(String formattedMessage) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Written to file: " + filePath + "\n" + formattedMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        asynLogProcessor.submitLogTask(runnable);
    }

    public FileAppender(String filePath, AsynLogProcessor asynLogProcessor) {
        this.asynLogProcessor = asynLogProcessor;
        this.filePath = filePath;
    }
}
