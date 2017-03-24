package com.lialzm.bean;

import com.lialzm.annotation.Status;
import com.lialzm.constraint.Group1;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by A on 2017/3/17.
 */
public class User implements Serializable {

    @NotBlank(message = "${user.id.null}")
    private String id;

    @Size(max = 50,min = 18,groups = Group1.class)
    private Integer age;

    private String phone;

    @Status
    private String status;

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
