package lesson4.multiple.locks.synchronizedblocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by lukasz.tomczyk on 17.01.2017.
 */
public class Worker {
    private List<Integer> listOne;
    private List<Integer> listTwo;
    private Random random;

    private Object lock1;
    private Object lock2;

    public Worker() {
        listOne = new ArrayList<>();
        listTwo = new ArrayList<>();
        random = new Random();
        lock1 = new Object();
        lock2 = new Object();
    }

    public void main() {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time take: " + (end - start));
        System.out.println("ListOne size: " + listOne.size() + "; " + "ListTwo size: " + listTwo.size());
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void stageOne() {
        synchronized (lock1) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            listOne.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized (lock2) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            listTwo.add(random.nextInt(100));
        }
    }
}
