package commands;

import managers.CollectionManager;
import io.WorkerReader;
import worker.Worker;

/**
 * Команда 'add_if_min'. Добавляет элемент, если он меньше минимального.
 */
public class AddIfMinCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final WorkerReader workerReader;

    public AddIfMinCommand(CollectionManager collectionManager, WorkerReader workerReader) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.workerReader = workerReader;
    }

    @Override
    public void execute(String argument) {
        Worker worker = workerReader.readWorker();
        if (collectionManager.addIfMin(worker)) {
            System.out.println("Рабочий успешно добавлен.");
        } else {
            System.out.println("Рабочий не добавлен: его значение не меньше минимального элемента.");
        }
    }
}
