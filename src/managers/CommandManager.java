package managers;
import java.util.Scanner;

/**
 * Класс для обработки команд, вводимых пользователем.
 */
public class CommandManager {
    private final CollectionManager collectionManager;
    private final Scanner scanner;

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Основной цикл программы, который ждет ввода команд.
     */
    public void interactiveMode() {
        System.out.println("Программа готова к работе. Введите 'help' для справки.");
        while (true) {
            System.out.println("> ");
            if (!scanner.hasNextLine()) {
                break;
            }


            String input = scanner.nextLine();
        }
    }
}