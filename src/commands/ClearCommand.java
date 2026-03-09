package commands;

import managers.CollectionManager;

/**
 * Команда 'clear'. Очищает коллекцию.
 */
public class ClearCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        collectionManager.clear();
    }
}
