package commands;

/**
 * Интерфейс для всех команд.
 */
public interface Command {
    void execute(String argument);
    String getName();
    String getDescription();
}
