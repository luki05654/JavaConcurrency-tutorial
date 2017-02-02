package lesson1.starting.threads;

import java.util.concurrent.TimeUnit;

/**
 * Created by lukasz.tomczyk on 18.01.2017.
 */
public class App {
    public static void main(String... args) {
        new RunnerOne().start();
        new Thread(new RunnerTwo()).start();
        new RunnerThree().start();
    }
}

class RunnerOne extends Thread {
    @Override
    public void run() {
        System.out.println("RunnerOne: " + "started");

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("RunnerOne: " + "completed");
    }
}

class RunnerTwo implements Runnable {

    @Override
    public void run() {
        System.out.println("RunnerTwo: " + "started");

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("RunnerTwo: " + "completed");
    }
}

class RunnerThree {
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("RunnerThree: " + "started");

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("RunnerThree: " + "completed");
        }
    });

    public void start() {
        thread.start();
    }
}
