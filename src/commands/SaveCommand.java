package commands;

import managers.CollectionManager;

/**
 * Команда 'save'. Сохраняет коллекцию в файл.
 */
public class SaveCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        collectionManager.save();
    }
}
