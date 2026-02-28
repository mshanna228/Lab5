package worker;

public class Organization {
    private Integer employeesCount; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null

    public Organization(Integer employeesCount, OrganizationType type) {
        set
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }


}