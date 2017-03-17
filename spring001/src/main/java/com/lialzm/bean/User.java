package com.lialzm.bean;

import java.io.Serializable;

/**
 * Created by A on 2017/3/17.
 */
public class User implements Serializable {

    private String id;

    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", role=" + role +
                '}';
    }
}
