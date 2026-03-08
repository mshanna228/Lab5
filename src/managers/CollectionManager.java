package managers;

import worker.Worker;
import java.time.LocalDateTime;
import java.util.PriorityQueue;

/**
 * Класс для управления коллекцией рабочих.
 */
public class CollectionManager {
    private PriorityQueue<Worker> collection = new PriorityQueue<>();
    private LocalDateTime lastInitTime;

    public CollectionManager() {
        this.lastInitTime = LocalDateTime.now();
    }

    /**
     * Выводит информацию о коллекции (тип, дата инициализации, количество элементов)
     */
    public void info() {
        System.out.println("Информация о коллекции:");
        System.out.println(" Тип: " + collection.getClass().getSimpleName());
        System.out.println(" Дата инициализации: " + lastInitTime);
        System.out.println(" Количество элементов: " + collection.size());
    }

    /**
     * Выводит все элементы коллекции в строковом представлении
     */
    public void show() {
        if (collection.isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            collection.forEach(System.out::println);
        }
    }

    /**
     * Добавляет нового рабочего в коллекцию
     */
    public void add(Worker worker) {
        collection.add(worker);
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Удаляет первый элемент коллекции/очередиы
     */
    public void removeFirst() {
        if (!collection.isEmpty()) {
            collection.poll();
            System.out.println("Первый элемент успешно удален.");
        } else {
            System.out.println("Коллекция пуста, удалять нечего.");
        }
    }

    public PriorityQueue<Worker> getCollection() {
        return collection;
    }
}