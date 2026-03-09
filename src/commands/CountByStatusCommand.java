package commands;

import managers.CollectionManager;
import worker.Status;

/**
 * Команда 'count_by_status'. Выводит количество элементов с заданным статусом.
 */
public class CountByStatusCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public CountByStatusCommand(CollectionManager collectionManager) {
        super("count_by_status status", "вывести количество элементов, значение поля status которых равно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.out.println("Ошибка: введите статус.");
            return;
        }
        try {
            Status status = Status.valueOf(argument.toUpperCase());
            int count = collectionManager.countByStatus(status);
            System.out.println("Количество рабочих со статусом " + status + ": " + count);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: некорректный статус.");
        }
    }
}
