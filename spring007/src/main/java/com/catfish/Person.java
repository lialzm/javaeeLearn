package com.catfish;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by apple on 17/4/16.
 */
public class Person {

    private String name;

    private Integer age;

    private Integer sex;

    private List<Book> mathBooks;

    private Set<Book> ChineseBooks;

    private Map<String,String> map;

    private String[] strings;

    private Book book;

    public Person(Integer age) {
        this.age = age;
    }

    public Person(Integer age, Integer sex) {
        this.age = age;
        this.sex = sex;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getMathBooks() {
        return mathBooks;
    }

    public void setMathBooks(List<Book> mathBooks) {
        this.mathBooks = mathBooks;
    }

    public Set<Book> getChineseBooks() {
        return ChineseBooks;
    }

    public void setChineseBooks(Set<Book> chineseBooks) {
        ChineseBooks = chineseBooks;
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

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }
}
