package commands;

import managers.CollectionManager;

/**
 * Команда 'average_of_salary'. Выводит среднюю зарплату.
 */
public class AverageOfSalaryCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public AverageOfSalaryCommand(CollectionManager collectionManager) {
        super("average_of_salary", "вывести среднее значение поля salary для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        double avg = collectionManager.averageOfSalary();
        System.out.println("Средняя зарплата: " + avg);
    }
}
