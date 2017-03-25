package com.lialzm.bean;

import com.lialzm.annotation.Status;
import com.lialzm.constraint.Group1;
import com.lialzm.constraint.Group2;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by A on 2017/3/17.
 */
@GroupSequence({Group1.class,Group2.class,User.class})
public class User implements Serializable {

    @NotBlank(message = "${user.id.null}")
    private String id;

    @Max(value = 50, groups = Group1.class)
    @Min(value = 18, groups = Group1.class)
    private Integer age;

    @Size(min = 11, max = 11,groups = Group2.class)
    private String phone;

    @Status
    private String status;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }
}
