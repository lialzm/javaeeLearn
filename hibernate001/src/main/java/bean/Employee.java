package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by A on 2016/12/2.
 */
public class Employee implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private int departmentId;
    private int card_id;
    private List<String> phone;
    private CardEntity cardEntity;
    private Department department;

    private Set<AddressEntity> addressEntities;

    public Employee() {
    }

    public Employee(String fname, String lname, int salary, int departmentId) {
        this.firstName = fname;
        this.lastName = lname;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public Employee(int id, String firstName, String lastName, int salary, int departmentId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public Set<AddressEntity> getAddressEntities() {
        return addressEntities;
    }

    public void setAddressEntities(Set<AddressEntity> addressEntities) {
        this.addressEntities = addressEntities;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }



    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                ", phone=" + phone +
                '}';
    }
}
