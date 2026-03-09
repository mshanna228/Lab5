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
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла");
        this.scriptReader = scriptReader;
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.out.println("Ошибка: введите имя файла.");
            return;
        }
        try {
            scriptReader.pushFile(argument);
            System.out.println("Выполнение скрипта " + argument + "...");
            // CommandManager.interactiveMode() will continue reading from scriptReader if it's set up correctly
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
