package commands;

import managers.CollectionManager;
import worker.Worker;

/**
 * Команда 'head'. Выводит первый элемент коллекции.
 */
public class HeadCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public HeadCommand(CollectionManager collectionManager) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        Worker head = collectionManager.head();
        if (head != null) {
            System.out.println(head);
        } else {
            System.out.println("Коллекция пуста.");
        }
    }
}
