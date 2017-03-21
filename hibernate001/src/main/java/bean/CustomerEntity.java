package bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by apple on 16/12/18.
 */
public class CustomerEntity {
    private int id;
    private int age;
    private Date birth;
    private Serializable createTime;
    private String email;
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Serializable getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Serializable createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (id != that.id) return false;
        if (age != that.age) return false;
        if (birth != null ? !birth.equals(that.birth) : that.birth != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + age;
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
