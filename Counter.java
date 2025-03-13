import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private int count = 0; // общий счётчик
    private final Lock lock = new ReentrantLock(); // замок для синхронизации

    public void increment() {
        lock.lock(); // захватываем замок
        try {
            count++; // увеличиваем счётчик
        } finally {
            lock.unlock(); // освобождаем замок
        }
    }

    public int getCount() {
        return count;
    }
}