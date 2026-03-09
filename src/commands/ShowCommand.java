package commands;

import managers.CollectionManager;

/**
 * Команда 'show'. Выводит все элементы коллекции.
 */
public class ShowCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        collectionManager.show();
    }
}
