package lesson8.wait.notify;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by lukasz.tomczyk on 18.01.2017.
 */
public class Processor {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer is running...");
            wait();
            System.out.println("Producer is resumed");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        TimeUnit.MILLISECONDS.sleep(2000);

        synchronized (this) {
            System.out.println("Waiting for return key...");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify();
            TimeUnit.MILLISECONDS.sleep(5000);
        }
    }
}
