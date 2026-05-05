package commands;

import managers.CollectionManager;
import managers.CommandManager;
import io.WorkerReader;
import io.ScriptReader;

/**
 * Перечисление типов команд для автоматической регистрации.
 */
public enum CommandType {
    INFO,
    SHOW,
    ADD,
    UPDATE,
    REMOVE_BY_ID,
    CLEAR,
    SAVE,
    EXECUTE_SCRIPT,
    EXIT,
    HEAD,
    ADD_IF_MAX,
    ADD_IF_MIN,
    AVERAGE_OF_SALARY,
    COUNT_BY_STATUS,
    FILTER_BY_SALARY;

    /**
     * Создается экземпляр команды.
     *
     * @param collectionManager Менеджер коллекции
     * @param workerReader Читатель данных работника
     * @param scriptReader Читатель скриптов
     * @param commandManager Менеджер команд (для execute_script)
     * @return Экземпляр команды
     */
    public Command create(CollectionManager collectionManager, WorkerReader workerReader,
                          ScriptReader scriptReader, CommandManager commandManager) {
        switch (this) {
            case INFO:
                return new InfoCommand(collectionManager);
            case SHOW:
                return new ShowCommand(collectionManager);
            case ADD:
                return new AddCommand(collectionManager, workerReader);
            case UPDATE:
                return new UpdateCommand(collectionManager, workerReader);
            case REMOVE_BY_ID:
                return new RemoveByIdCommand(collectionManager);
            case CLEAR:
                return new ClearCommand(collectionManager);
            case SAVE:
                return new SaveCommand(collectionManager);
            case EXECUTE_SCRIPT:
                return new ExecuteScriptCommand(scriptReader, commandManager);
            case EXIT:
                return new ExitCommand();
            case HEAD:
                return new HeadCommand(collectionManager);
            case ADD_IF_MAX:
                return new AddIfMaxCommand(collectionManager, workerReader);
            case ADD_IF_MIN:
                return new AddIfMinCommand(collectionManager, workerReader);
            case AVERAGE_OF_SALARY:
                return new AverageOfSalaryCommand(collectionManager);
            case COUNT_BY_STATUS:
                return new CountByStatusCommand(collectionManager);
            case FILTER_BY_SALARY:
                return new FilterBySalaryCommand(collectionManager);
            default:
                throw new IllegalArgumentException("Unknown command type: " + this);
        }
    }
}
