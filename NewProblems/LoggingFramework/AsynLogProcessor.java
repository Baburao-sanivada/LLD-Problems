package LoggingFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynLogProcessor {
    private ExecutorService executor;

    public AsynLogProcessor(int threadPoolSize) {
        this.executor = Executors.newFixedThreadPool(3);
    }


    public void submitLogTask(Runnable logTask) {
        executor.submit(logTask);
    }

    public void stop() {
        executor.shutdown();
    }
}
