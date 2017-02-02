package lesson9.lowlevel.synchronization;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by lukasz.tomczyk on 18.01.2017.
 */
public class Processor {
    private LinkedList<Integer> linkedList = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while(linkedList.size() == LIMIT) {
                    lock.wait();
                    System.out.println("PRODUCER: List is FULL: Waiting for Consumer\n");
                }
                linkedList.add(value++);
                System.out.println("PRODUCER: Added value to list");
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while(linkedList.size() == 0) {
                    lock.wait();
                    System.out.println("CONSUMER: List is EMPTY: Waiting for Producer\n");
                }

                System.out.print("CONSUMER: List size is: " + linkedList.size());
                int value = linkedList.removeFirst();
                System.out.println("; taken value is: " + value);
                lock.notify();
            }

            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
        }
    }
}
