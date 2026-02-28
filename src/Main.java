import worker.Worker;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: введите имя файла с данными в качестве аргумента командной строки.");
            System.exit(1);
        }
        String fileName = args[0];


    }
}