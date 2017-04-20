package com.catfish;

/**
 * Created by apple on 17/4/16.
 */
public class Person {

    private String name;

    private Integer age;


    private Integer sex;

    public Person(Integer age) {
        this.age = age;
    }

    public Person(Integer age, Integer sex) {
        this.age = age;
        this.sex = sex;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
