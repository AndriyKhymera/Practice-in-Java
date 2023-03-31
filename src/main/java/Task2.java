import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Task2 {

    private Processor processor;
    private CountDownLatch latch = new CountDownLatch(1);

    private class Processor {
        public void init() {
            log.info("Processor: initialization is done.");
        }

        public void process() {
            log.info("Processor: processing is done.");
        }
    }

    protected class Initializer implements Runnable {
        public void run() {
            log.info("Initializer: initializing...");
            processor = new Processor();
            processor.init();
            latch.countDown();
        }
    }

    protected class Executor implements Runnable {
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Executor: processing..");
            processor.process();
        }
    }
}
