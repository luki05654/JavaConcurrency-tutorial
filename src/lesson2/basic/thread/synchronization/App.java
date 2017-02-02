package lesson2.basic.thread.synchronization;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by lukasz.tomczyk on 17.01.2017.
 */
public class App {
    public static void main(String... args) {
        Processor processor = new Processor();
        processor.start();

        System.out.println("Naciśnij enter, aby zakończyć...");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();
    }
}

class Processor extends Thread {
    private volatile boolean isRunning;

    public Processor() {
        this.isRunning = true;
    }

    @Override
    public void run() {
        while(isRunning) {
            System.out.println("Hello");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        this.isRunning = false;
    }
}
