package commands;

/**
 * Команда 'exit'. Завершает программу.
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String argument) {
        System.out.println("Завершение работы...");
        System.exit(0);
    }
}
