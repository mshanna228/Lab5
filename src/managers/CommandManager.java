package managers;

import commands.*;
import io.ConsoleInputManager;
import io.ScriptReader;
import io.WorkerReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для регистрации команд и управления командами.
 *  команды сохр. в HashMap<String, Command>,  ключ — имя команды в ниж. регистре.
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final ConsoleInputManager consoleInputManager;
    private final ScriptReader scriptReader;

    public CommandManager(CollectionManager collectionManager, ConsoleInputManager consoleInputManager,
            WorkerReader workerReader, ScriptReader scriptReader) {
        this.consoleInputManager = consoleInputManager;
        this.scriptReader = scriptReader;

        for (CommandType type : CommandType.values()) {
            Command command = type.create(collectionManager, workerReader, scriptReader, this);
            register(command.getName().split(" ")[0], command);
        }
        register("help", new HelpCommand(commands.values()));
    }

    /**
     * метод register  добавление команд в хран.
     * программы
     * 
     * @param name
     * @param command
     */
    private void register(String name, Command command) {
        commands.put(name, command);
    }

    /**
     *
     */
    public void interactiveMode() {
        System.out.println("=========================================");
        System.out.println("Программа работает. Введите 'help'.");
        System.out.println("=========================================");

        while (true) {
            String input;
            if (!scriptReader.isEmpty()) {
                input = scriptReader.readLine();
                if (input == null)
                    continue;
                System.out.println("> " + input);
            } else {
                input = consoleInputManager.readLine(">");
            }

            if (input == null)
                break;
            if (input.isEmpty())
                continue;

            String[] tokens = input.split("\\s+", 2); //елим строку по пробелам на две части tokens[0] и tokens[1]
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