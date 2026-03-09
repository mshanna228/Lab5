package managers;

import worker.Worker;
import java.util.PriorityQueue;

/**
 * Интерфейс для работы с коллекцией.
 */
public interface CollectionManager {
    void info();
    void show();
    void add(Worker worker);
    void update(long id, Worker worker);
    void removeById(long id);
    void clear();
    void save();
    Worker head();
    boolean addIfMax(Worker worker);
    boolean addIfMin(Worker worker);
    double averageOfSalary();
    int countByStatus(worker.Status status);
    void filterBySalary(Integer salary);
    PriorityQueue<Worker> getCollection();
}