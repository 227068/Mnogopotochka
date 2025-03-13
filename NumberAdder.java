import java.util.concurrent.CopyOnWriteArrayList;

class NumberAdder extends Thread {
    private final CopyOnWriteArrayList<Integer> list;

    public NumberAdder(CopyOnWriteArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            list.add(i); // добавляем число в список
        }
    }
}

