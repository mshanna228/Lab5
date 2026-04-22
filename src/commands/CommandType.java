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
        return switch (this) {
            case INFO -> new InfoCommand(collectionManager);
            case SHOW -> new ShowCommand(collectionManager);
            case ADD -> new AddCommand(collectionManager, workerReader);
            case UPDATE -> new UpdateCommand(collectionManager, workerReader);
            case REMOVE_BY_ID -> new RemoveByIdCommand(collectionManager);
            case CLEAR -> new ClearCommand(collectionManager);
            case SAVE -> new SaveCommand(collectionManager);
            case EXECUTE_SCRIPT -> new ExecuteScriptCommand(scriptReader, commandManager);
            case EXIT -> new ExitCommand();
            case HEAD -> new HeadCommand(collectionManager);
            case ADD_IF_MAX -> new AddIfMaxCommand(collectionManager, workerReader);
            case ADD_IF_MIN -> new AddIfMinCommand(collectionManager, workerReader);
            case AVERAGE_OF_SALARY -> new AverageOfSalaryCommand(collectionManager);
            case COUNT_BY_STATUS -> new CountByStatusCommand(collectionManager);
            case FILTER_BY_SALARY -> new FilterBySalaryCommand(collectionManager);
        };
    }
}
