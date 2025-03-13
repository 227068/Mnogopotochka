import java.util.List;

public class Manager extends Worker {
    private List<Worker> subordinates;

    // Конструктор
    public Manager(String name, int age, String gender, double salary, List<Worker> subordinates) {
        super(name, age, gender, salary);
        this.subordinates = subordinates;
    }

    // Метод для вывода информации о менеджере
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Subordinates: ");
        for (Worker worker : subordinates) {
            System.out.println(" - " + worker.getName());
        }
    }

    // Геттер и сеттер для подчиненных
    public List<Worker> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Worker> subordinates) {
        this.subordinates = subordinates;
    }
}


