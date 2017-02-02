package lrsson14.interrupting.threads;

import java.util.concurrent.*;

/**
 * Created by lukasz.tomczyk on 25.01.2017.
 */
public class App {
    public static void main(String... args) throws InterruptedException {
        //oneWay();
        secondWay();
    }

    private static void oneWay() throws InterruptedException {
        System.out.println("Starting");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1E9; i++) {
                    if(Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread is interrupted");
                        break;
                    }
                }
            }
        });

        thread.start();

        TimeUnit.MILLISECONDS.sleep(500);
        thread.interrupt();
        thread.join();

        System.out.println("Finished");
    }

    private static void secondWay() throws InterruptedException {
        System.out.println("Starting");

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<?> future = executorService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0; i < 1E9; i++) {
                    if(Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread is interrupted");
                        break;
                    }
                }
                return null;
            }
        });

        executorService.shutdown();

        TimeUnit.MILLISECONDS.sleep(500);

        executorService.shutdownNow();
        //future.cancel(true);

        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finished");
    }
}
