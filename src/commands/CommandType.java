package commands;

import io.ScriptReader;
import managers.CommandManager;

public enum CommandType {
    INFO, SHOW, ADD, UPDATE, REMOVE_BY_ID, CLEAR, SAVE, EXECUTE_SCRIPT, EXIT, HEAD,
    ADD_IF_MAX, ADD_IF_MIN, AVERAGE_OF_SALARY, COUNT_BY_STATUS, FILTER_BY_SALARY;

    public Command create(CollectionManager collectionManager, WorkerReader workerReader, ScriptReader scriptReader, CommandManager commandManager)
    {
        return switch (this) {
            case INFO -> new InfoCommand(collectionManager);
            case SHOW -> new ShowCommand(collectionManager, workerReader);
        }
    }
}
