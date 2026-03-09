package commands;

import managers.CollectionManager;
import io.WorkerReader;
import worker.Worker;
import exceptions.InvalidDataException;

/**
 * Команда 'update'. Обновляет значение элемента коллекции по ID.
 */
public class UpdateCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final WorkerReader workerReader;

    public UpdateCommand(CollectionManager collectionManager, WorkerReader workerReader) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.workerReader = workerReader;
    }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.out.println("Ошибка: введите ID для обновления.");
            return;
        }
        try {
            long id = Long.parseLong(argument);
            Worker worker = workerReader.readWorker();
            collectionManager.update(id, worker);
            System.out.println("Рабочий успешно обновлен.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
