package lesson12.semaphores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by lukasz.tomczyk on 25.01.2017.
 */
public class Connection {
    private static Connection ourInstance = new Connection();

    public static Connection getInstance() {
        return ourInstance;
    }

    private int connection;
    private Semaphore semaphore;

    private Connection() {
        connection = 0;
        semaphore = new Semaphore(10, true);
    }

    public void connect() {
        try {
            semaphore.acquire();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        }
        finally {
            semaphore.release();
        }
    }

    public void doConnect() {

        synchronized (this) {
            connection++;
            System.out.println("conncetions: " + connection);
        }

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connection--;
            //System.out.println("conncetions: " + connection);
        }
    }
}
