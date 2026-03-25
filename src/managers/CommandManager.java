package managers;

import commands.*;
import io.ConsoleInputManager;
import io.ScriptReader;
import io.WorkerReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс для управления командами.
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final ConsoleInputManager consoleInputManager;
    private final ScriptReader scriptReader;

    public CommandManager(CollectionManager collectionManager, ConsoleInputManager consoleInputManager, 
                          WorkerReader workerReader, ScriptReader scriptReader) {
        this.consoleInputManager = consoleInputManager;
        this.scriptReader = scriptReader;

        register("info", new InfoCommand(collectionManager));
        register("show", new ShowCommand(collectionManager));
        register("add", new AddCommand(collectionManager, workerReader));
        register("update", new UpdateCommand(collectionManager, workerReader));
        register("remove_by_id", new RemoveByIdCommand(collectionManager));
        register("clear", new ClearCommand(collectionManager));
        register("save", new SaveCommand(collectionManager));
        register("execute_script", new ExecuteScriptCommand(scriptReader, this));
        register("exit", new ExitCommand());
        register("head", new HeadCommand(collectionManager));
        register("add_if_max", new AddIfMaxCommand(collectionManager, workerReader));
        register("add_if_min", new AddIfMinCommand(collectionManager, workerReader));
        register("average_of_salary", new AverageOfSalaryCommand(collectionManager));
        register("count_by_status", new CountByStatusCommand(collectionManager));
        register("filter_by_salary", new FilterBySalaryCommand(collectionManager));
    }

    private void register(String name, Command command) {
        commands.put(name, command);
    }

    public void interactiveMode() {
        System.out.println("Программа готова к работе. Введите 'help' для справки.");
        while (true) {
            String input;
            if (!scriptReader.isEmpty()) {
                input = scriptReader.readLine();
                if (input == null) continue;
                System.out.println("> " + input);
            } else {
                input = consoleInputManager.readLine(">");
            }

            if (input == null) break;
            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+", 2);
            String commandName = tokens[0].toLowerCase();
            String argument = tokens.length > 1 ? tokens[1] : "";

            Command command = commands.get(commandName);
            if (command != null) {
                try {
                    command.execute(argument);
                } catch (Exception e) {
                    System.err.println("Ошибка при выполнении команды: " + e.getMessage());
                }
            } else {
                System.out.println("Неизвестная команда: '" + commandName + "'. Введите 'help' для списка всех команд.");
            }
        }
    }
}