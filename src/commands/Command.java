package commands;

/**
 * Интерфейс для всех команд.
 *     void execute(String argument);
 *      String getName();
 *     String getDescription();
 */
public interface Command {
    void execute(String argument);
    String getName();
    String getDescription();
}
