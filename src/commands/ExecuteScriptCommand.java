package commands;

import io.ScriptReader;
import managers.CommandManager;
import java.io.FileNotFoundException;

/**
 * Команда 'execute_script'. Выполняет команды из файла.
 */
public class ExecuteScriptCommand extends AbstractCommand {
    private final ScriptReader scriptReader;
    private final CommandManager commandManager;

    public ExecuteScriptCommand(ScriptReader scriptReader, CommandManager commandManager) {
        super("execute_script file_name", "исполнить скрипт из файла");
        this.scriptReader = scriptReader;
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String argument) {
//        File scriptFile = new File(filname);
        if (argument.isEmpty()) {
            System.out.println("Ошибка: введите имя файла.");
            return;
        }

        String[] parts = argument.trim().split("\\s+");
        String fileName = parts[0];
        String[] scriptArgs = new String[parts.length - 1];
        System.arraycopy(parts, 1, scriptArgs, 0, parts.length - 1);

        try {
            scriptReader.pushFile(fileName, scriptArgs);
            System.out.println("Выполнение скрипта " + fileName + " (аргументов: " + scriptArgs.length + ")...");
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
