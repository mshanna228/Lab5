package io;

import worker.*;
import java.util.Arrays;

/**
 * Класс для чтения объектов Worker из консоли.
 */
public class WorkerReader {
    private final ConsoleInputManager inputManager;

    public WorkerReader(ConsoleInputManager inputManager) {
        this.inputManager = inputManager;
    }

    public Worker readWorker() {
        String name = readString("Введите имя рабочего:", false);
        Coordinates coordinates = readCoordinates();
        Integer salary = readInt("Введите зарплату:", true);
        Position position = readEnum("Введите должность (LABORER, LEAD_DEVELOPER, CLEANER):", Position.class, false);
        Status status = readEnum("Введите статус (FIRED, HIRED, RECOMMENDED_FOR_PROMOTION, REGULAR, PROBATION):", Status.class, true);
        Organization organization = readOrganization();

        return new Worker(name, coordinates, salary, position, status, organization);
    }

    public Coordinates readCoordinates() {
        Integer x = readInt("Введите координату X (макс. 709):", false);
        while (x > 709) {
            System.out.println("Ошибка: X не может быть больше 709.");
            x = readInt("Введите координату X (макс. 709):", false);
        }
        Double y = readDouble("Введите координату Y (больше -414):", false);
        while (y <= -414) {
            System.out.println("Ошибка: Y должен быть больше -414.");
            y = readDouble("Введите координату Y (больше -414):", false);
        }
        return new Coordinates(x, y);
    }

    public Organization readOrganization() {
        Integer count = readInt("Введите количество сотрудников в организации:", true);
        while (count != null && count <= 0) {
            System.out.println("Ошибка: количество сотрудников должно быть больше 0.");
            count = readInt("Введите количество сотрудников в организации:", true);
        }
        OrganizationType type = readEnum("Введите тип организации (COMMERCIAL, GOVERNMENT, TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY):", OrganizationType.class, false);
        return new Organization(count, type);
    }

    private String readString(String message, boolean canBeNull) {
        while (true) {
            String s = inputManager.readLine(message);
            if (s == null || s.isEmpty()) {
                if (canBeNull) return null;
                System.out.println("Ошибка: поле не может быть пустым.");
                continue;
            }
            return s;
        }
    }

    private Integer readInt(String message, boolean canBeNull) {
        while (true) {
            String s = inputManager.readLine(message);
            if (s == null || s.isEmpty()) {
                if (canBeNull) return null;
                System.out.println("Ошибка: поле не может быть пустым.");
                continue;
            }
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private Double readDouble(String message, boolean canBeNull) {
        while (true) {
            String s = inputManager.readLine(message);
            if (s == null || s.isEmpty()) {
                if (canBeNull) return null;
                System.out.println("Ошибка: поле не может быть пустым.");
                continue;
            }
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    private <E extends Enum<E>> E readEnum(String message, Class<E> enumClass, boolean canBeNull) {
        while (true) {
            String s = inputManager.readLine(message);
            if (s == null || s.isEmpty()) {
                if (canBeNull) return null;
                System.out.println("Ошибка: поле не может быть пустым.");
                continue;
            }
            try {
                return Enum.valueOf(enumClass, s.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: введите значение из списка: " + Arrays.toString(enumClass.getEnumConstants()));
            }
        }
    }
}
