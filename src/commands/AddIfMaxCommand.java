package commands;

import managers.CollectionManager;
import io.WorkerReader;
import worker.Worker;

/**
 * Команда 'add_if_max'. Добавляет элемент, если он больше максимального.
 */
public class AddIfMaxCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final WorkerReader workerReader;

    public AddIfMaxCommand(CollectionManager collectionManager, WorkerReader workerReader) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.workerReader = workerReader;
    }

    @Override
    public void execute(String argument) {
        Worker worker = workerReader.readWorker();
        if (collectionManager.addIfMax(worker)) {
            System.out.println("Рабочий успешно добавлен.");
        } else {
            System.out.println("Рабочий не добавлен: его значение не превышает максимальный элемент.");
        }
    }
}
