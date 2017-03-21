package bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

/**
 * Created by A on 2016/12/12.
 */

public class Department implements Serializable {

    private int id;
    private String name;

    private BigInteger count;


    private Set<Employee> employees;

    public Department() {

    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", employees=" + employees +
                '}';
    }
}
