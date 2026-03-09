package commands;

import managers.CollectionManager;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции по ID.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.out.println("Ошибка: введите ID для удаления.");
            return;
        }
        try {
            long id = Long.parseLong(argument);
            collectionManager.removeById(id);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть числом.");
        }
    }
}
