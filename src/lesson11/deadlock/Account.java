package lesson11.deadlock;

/**
 * Created by lukasz.tomczyk on 19.01.2017.
 */
public class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }

    public int getBalance() {
        return balance;
    }
}
