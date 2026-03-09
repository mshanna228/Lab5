package commands;

import managers.CollectionManager;

/**
 * Команда 'filter_by_salary'. Выводит элементы с заданной зарплатой.
 */
public class FilterBySalaryCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public FilterBySalaryCommand(CollectionManager collectionManager) {
        super("filter_by_salary salary", "вывести элементы, значение поля salary которых равно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) {
        if (argument.isEmpty()) {
            System.out.println("Ошибка: введите зарплату для фильтрации.");
            return;
        }
        try {
            Integer salary = Integer.parseInt(argument);
            collectionManager.filterBySalary(salary);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: зарплата должна быть числом.");
        }
    }
}
