package com.catfish.bean;

import java.io.Serializable;

/**
 * Created by A on 2017/4/1.
 */
public class User implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }
}
