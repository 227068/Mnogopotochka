import java.util.concurrent.Semaphore;
class Resource {
    private final Semaphore semaphore;

    public Resource(int maxConcurrentAccess) {
        this.semaphore = new Semaphore(maxConcurrentAccess);
    }

    public void useResource(int threadId) {
        try {
            // Попытка получить доступ к ресурсу
            System.out.println("Поток " + threadId + " пытается получить доступ к ресурсу.");
            semaphore.acquire(); // Запрашиваем разрешение

            // Используем ресурс
            System.out.println("Поток " + threadId + " получил доступ к ресурсу.");
            Thread.sleep((long) (Math.random() * 2000)); // Симуляция работы с ресурсом
            System.out.println("Поток " + threadId + " освободил ресурс.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Освобождаем разрешение
        }
    }
}
