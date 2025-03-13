import java.util.concurrent.locks.ReentrantLock;

class Account {
    private final String name;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public Account(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public void transfer(Account target, double amount) {
        // Сначала блокируем текущий аккаунт, затем целевой
        boolean firstLock = false;
        boolean secondLock = false;

        try {
            while (true) {
                try {
                    firstLock = lock.tryLock();
                    secondLock = target.lock.tryLock();
                    if (firstLock && secondLock) {
                        if (balance >= amount) {
                            balance -= amount;
                            target.balance += amount;
                            System.out.printf("Переведено %.2f от %s к %s. Новый баланс: %s - %.2f, %s - %.2f%n",
                                    amount, name, target.name, name, balance, target.name, target.balance);
                        } else {
                            System.out.printf("Недостаточно средств для перевода от %s к %s. Текущий баланс: %.2f%n",
                                    name, target.name, balance);
                        }
                        return;
                    }
                } finally {
                    if (firstLock) {
                        lock.unlock();
                    }
                    if (secondLock) {
                        target.lock.unlock();
                    }
                }
            }
        } finally {
            if (firstLock) {
                lock.unlock();
            }
            if (secondLock) {
                target.lock.unlock();
            }
        }
    }

    public double getBalance() {
        return balance;
    }
}

class TransferTask implements Runnable {
    private final Account fromAccount;
    private final Account toAccount;
    private final double amount;

    public TransferTask(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        fromAccount.transfer(toAccount, amount);
    }
}

public class BankExample {
    public static void main(String[] args) {
        Account account1 = new Account("Аккаунт 1", 1000);
        Account account2 = new Account("Аккаунт 2", 500);
        Account account3 = new Account("Аккаунт 3", 300);

        Thread t1 = new Thread(new TransferTask(account1, account2, 200));
        Thread t2 = new Thread(new TransferTask(account2, account3, 100));
        Thread t3 = new Thread(new TransferTask(account1, account3, 150));
        Thread t4 = new Thread(new TransferTask(account3, account1, 50));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Баланс %s: %.2f%n", account1.name, account1.getBalance());
        System.out.printf("Баланс %s: %.2f%n", account2.name, account2.getBalance());
        System.out.printf("Баланс %s: %.2f%n", account3.name, account3.getBalance());
    }
}

