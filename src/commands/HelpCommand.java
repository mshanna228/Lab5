package commands;

import java.util.Collection;

/**
 * Команда 'help'. Выводит справку.
 */
public class HelpCommand extends AbstractCommand {
    private final Collection<Command> commands;

    public HelpCommand(Collection<Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
    }

    @Override
    public void execute(String argument) {
        System.out.println("Справка по доступным командам:");
        for (Command command : commands) {
            System.out.println(" " + command.getName() + " : " + command.getDescription());
        }
    }
}
