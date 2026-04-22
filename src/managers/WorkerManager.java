package managers;

import worker.Worker;
import worker.Status;
import exceptions.InvalidDataException;
import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 *  менеджер коллекции.
 */
public class WorkerManager implements CollectionManager {
    private PriorityQueue<Worker> collection = new PriorityQueue<>();
    private final LocalDateTime lastInitTime;
    private final FileManager fileManager;

    public WorkerManager(FileManager fileManager) {
        this.lastInitTime = LocalDateTime.now();
        this.fileManager = fileManager;
    }

    @Override
    public void info() {
        System.out.println("Информация о коллекции:");
        System.out.println(" Тип: " + collection.getClass().getName());
        System.out.println(" Дата инициализации: " + lastInitTime);
        System.out.println(" Количество элементов: " + collection.size());
    }

    @Override
    public void show() {
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            collection.stream().sorted().forEach(System.out::println);
        }
    }

    @Override
    public void add(Worker worker) {
        collection.add(worker);
        IdManager.useId(worker.getId());
    }

    @Override
    public void update(long id, Worker worker) {
        Worker existing = collection.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElseThrow(() -> new InvalidDataException("Рабочий с ID " + id + " не найден."));
        collection.remove(existing);
        worker.setId(id);
        collection.add(worker);
    }

    @Override
    public void removeById(long id) {
        Worker worker = collection.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
        if (worker != null) {
            collection.remove(worker);
            IdManager.removeId(id);
            System.out.println("Рабочий удален.");
        } else {
            System.out.println("Рабочий с таким ID не найден.");
        }
    }

    @Override
    public void clear() {
        collection.clear();
        IdManager.clear();
        System.out.println("Коллекция очищена.");
    }

    @Override
    public void save() {
        fileManager.saveCollection(collection);
    }

    @Override
    public Worker head() {
        return collection.peek();
    }

    @Override
    public boolean addIfMax(Worker worker) {
        Worker max = collection.stream().max(Worker::compareTo).orElse(null);
        if (max == null || worker.compareTo(max) > 0) {
            add(worker);
            return true;
        }
        return false;
    }

    @Override
    public boolean addIfMin(Worker worker) {
        Worker min = collection.stream().min(Worker::compareTo).orElse(null);
        if (min == null || worker.compareTo(min) < 0) {
            add(worker);
            return true;
        }
        return false;
    }

    @Override
    public double averageOfSalary() {
        return collection.stream()
                .filter(w -> w.getSalary() != null)
                .mapToInt(Worker::getSalary)
                .average()
                .orElse(0);
    }

    @Override
    public int countByStatus(Status status) {
        return (int) collection.stream()
                .filter(w -> w.getStatus() == status)
                .count();
    }

    @Override
    public void filterBySalary(Integer salary) {
        collection.stream()
                .filter(w -> (salary == null && w.getSalary() == null) || (salary != null && salary.equals(w.getSalary())))
                .forEach(System.out::println);
    }

    @Override
    public PriorityQueue<Worker> getCollection() {
        return collection;
    }
}
