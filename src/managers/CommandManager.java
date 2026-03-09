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
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] tokens = input.split("\\s+", 2);
            String command = tokens[0].toLowerCase();

            switch (command) {
                case "help":
                    printHelp();
                    break;

                case "info":
                    collectionManager.info();
                    break;

                case "show":
                    collectionManager.show();
                    break;

                case "add":
                    // сюда добавить метод
                    System.out.println("Запуск нового рабочего...");
                    break;

                case "clear":
                    collectionManager.clear();
                    break;

                case "exit":
                    System.out.println("Завершение работы...");
                    return;

                default:
                    System.out.println("Неизвестная команда: '" + command + "'. Введите 'help' для списка всех команд."); 
            }
        }
    }


        private void printHelp() {
            System.out.println("Доступные команды:");
            System.out.println(" help : вывести справку по доступным командам");
            System.out.println(" info : вывести информацию о коллекции");
            System.out.println(" show : вывести все элементы коллекции");
            System.out.println(" add : добавить новый элемент в коллекцию");
            System.out.println(" clear : очистить коллекцию");
            System.out.println(" exit : завершить программу");
        }
}


//            case "execute_script":
//                if (tokens.length < 2) {
//                    System.out.println("Ошибка: введите имя файла после команды!");
//                } else {
//                    String fileName = tokens[1];
//                    // вызываем метод выполнения скрипта
//                }
//                break;