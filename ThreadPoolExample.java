import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Создаем пул из 4 потоков
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Выполняем 20 задач
        for (int i = 1; i <= 20; i++) {
            final int taskNumber = i; // для использования в лямбда-выражении
            executorService.submit(() -> {
                // Выводим имя потока и номер задачи
                System.out.println("Поток: " + Thread.currentThread().getName() + ", Задача номер: " + taskNumber);
            });
        }

        // Завершаем работу пула потоков
        executorService.shutdown();
    }
}
