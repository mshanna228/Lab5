package commands;

import managers.CollectionManager;
import io.WorkerReader;
import worker.Worker;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 */
public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final WorkerReader workerReader;

    public AddCommand(CollectionManager collectionManager, WorkerReader workerReader) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.workerReader = workerReader;
    }

    @Override
    public void execute(String argument) {
        Worker worker = workerReader.readWorker();
        collectionManager.add(worker);
        System.out.println("Рабочий успешно добавлен.");
    }

//    System.out.println(coll)
}
