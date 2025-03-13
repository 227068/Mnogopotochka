import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Task implements Runnable {
    private final int id;
    private final CyclicBarrier barrier;

    public Task(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            // Симуляция выполнения задачи
            System.out.println("Поток " + id + " выполняет свою задачу.");
            Thread.sleep((long) (Math.random() * 1000)); // Задержка для имитации работы
            System.out.println("Поток " + id + " завершил свою задачу.");

            // Ждем, пока все потоки достигнут барьера
            barrier.await();

            // Все потоки достигли барьера и продолжают работу
            System.out.println("Поток " + id + " переходит к следующей фазе работы.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierExample {
    public static void main(String[] args) {
        final int numberOfThreads = 5;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () -> {
            System.out.println("Все потоки завершили свои задачи. Переходим к следующей фазе работы.");
        });

        // Создаем и запускаем потоки
        for (int i = 1; i <= numberOfThreads; i++) {
            new Thread(new Task(i, barrier)).start();
        }
    }
}

