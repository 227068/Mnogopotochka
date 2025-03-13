public class SemaphoreExample {
    public static void main(String[] args) {
        Resource resource = new Resource(2); // Ограничиваем доступ к ресурсу для 2 потоков

        // Создаем и запускаем 5 потоков
        for (int i = 1; i <= 5; i++) {
            new WorkerThread(resource, i).start();
        }
    }
}
