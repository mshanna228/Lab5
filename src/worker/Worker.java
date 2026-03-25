package worker;
import java.util.Date;

import managers.IdManager;

public class Worker implements Comparable<Worker> {

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer salary; //Поле может быть null, Значение поля должно быть больше 0
    private Position position; //Поле не может быть null
    private Status status; //Поле может быть null
    private Organization organization; //Поле не может быть null

    /**
     * Конструктор для создания нового объекта Worker
     * @param name
     * @param coordinates
     * @param salary
     * @param position
     * @param status
     * @param organization
     */
    public Worker(String name, Coordinates coordinates, Integer salary, Position position, Status status, Organization organization) {
        // Автогенерация
        this.id = IdManager.generateId();
        this.creationDate = new Date();

        setName(name);
        setCoordinates(coordinates);
        setSalary(salary);
        setPosition(position);
        setStatus(status);
        setOrganization(organization);
    }


    public Worker() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id <= 0) {
            throw new exceptions.InvalidDataException("ID должен быть больше 0");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new exceptions.InvalidDataException("Имя не может быть пустым или null");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new exceptions.InvalidDataException("Coordinates не могут быть null");
        }
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate == null) {
            throw new exceptions.InvalidDataException("creationDate не может быть null");
        }
        this.creationDate = creationDate;
    }

    public void setSalary(Integer salary) {
        if (salary != null && salary <= 0) {
            throw new exceptions.InvalidDataException("Salary должно быть больше 0");
        }
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setPosition(Position position) {
        if (position == null) {
            throw new exceptions.InvalidDataException("Position не может быть null");
        }
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setOrganization(Organization organization) {
        if (organization == null) {
            throw new exceptions.InvalidDataException("Organization не может быть null");
        }
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }

    @Override
    public int compareTo(Worker other)
    {
        return Long.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + (coordinates != null ? "X:" + coordinates.getX() + " Y:" + coordinates.getY() : "null") +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", position=" + position +
                ", status=" + status +
                ", organization=" + (organization != null ? "Count:" + organization.getEmployeesCount() + " Type:" + organization.getType() : "null") +
                '}';
    }
}
