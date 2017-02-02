package lesson9.lowlevel.synchronization;

/**
 * Created by lukasz.tomczyk on 18.01.2017.
 */
public class App {
    public static void main(String... args) throws InterruptedException {
        Processor processor = new Processor();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
