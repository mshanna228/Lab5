import managers.*;
import io.*;
import worker.*;

import java.util.Comparator;
import java.util.Scanner;

/**
 * Точка входа в программу.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {                   //1
            System.err.println("Ошибка: имя файла должно передаваться как аргумент командной строки.");
            System.exit(1);
        }







        String fileName = args[0];
        FileManager fileManager = new FileManager(fileName); //2
        WorkerManager workerManager = new WorkerManager(fileManager);

        // авт. заполнение из файла
        workerManager.getCollection().addAll(fileManager.readCollection());



        ConsoleInputManager consoleInputManager = new ConsoleInputManager(new Scanner(System.in));
        ScriptReader scriptReader = new ScriptReader();

//  scriptReader Передаем в конструктор WorkerReader!!!!!!!!!!!!!!!!!!!
        WorkerReader workerReader = new WorkerReader(consoleInputManager, scriptReader);

        CommandManager commandManager = new CommandManager(workerManager, consoleInputManager, workerReader, scriptReader);


        commandManager.interactiveMode();
    }
}