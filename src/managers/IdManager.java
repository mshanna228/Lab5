package managers;

import java.util.HashSet;
import java.util.Set;

/**
 * Менеджер для генерации и контроля уникальности ID.
 */
public class IdManager {
    private static long nextId = 1;
    private static final Set<Long> usedIds = new HashSet<>();

    /**
     * Генерирует новый уникальный ID.
     * @return новый ID.
     */
    public static long generateId() {
        while (usedIds.contains(nextId)) {
            nextId++;
        }
        usedIds.add(nextId);
        return nextId++;
    }

    /**
     * Проверяет, занят ли ID.
     * @param id ID для проверки.
     * @return true, если занят.
     */
    public static boolean isIdUsed(long id) {
        return usedIds.contains(id);
    }

    /**
     * Добавляет ID в список использованных.
     * @param id ID для добавления.
     */
    public static void useId(long id) {
        usedIds.add(id);
    }

    /**
     * Удаляет ID из списка использованных.
     * @param id ID для удаления.
     */
    public static void removeId(long id) {
        usedIds.remove(id);
    }

    /**
     * Очищает занятые ID.
     */
    public static void clear() {
        usedIds.clear();
        nextId = 1;
    }
}
