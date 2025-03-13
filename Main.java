public class Main {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(); // создаем потокобезопасный список

        // создаем и запускаем 10 потоков
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new NumberAdder(list);
            threads[i].start();
        }

        // ждем завершения всех потоков
        for (Thread thread : threads) {
            thread.join();
        }

        // выводим итоговый размер списка и его содержимое
        System.out.println("Итоговый размер списка: " + list.size());
        System.out.println("Содержимое списка: " + list);
    }
}
