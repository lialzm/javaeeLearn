package com.lialzm.bean;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by A on 2017/3/17.
 */
public class User implements Serializable {

    @NotBlank
    private String id;


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
