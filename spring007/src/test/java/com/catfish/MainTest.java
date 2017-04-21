package com.catfish;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by apple on 17/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-applicationContext.xml"})
@WebAppConfiguration
public class MainTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    Person person;

    @Test
    public void testSetter() {
        Assert.assertEquals("小美", person.getName());
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals("10", String.valueOf(person.getAge()));
    }

    @Test
    public void testConstructor2() {
        Assert.assertEquals("0", String.valueOf(person.getSex()));
    }

    @Test
    public void testList() {
        List<Book> list = person.getMathBooks();
        System.out.println(list);
    }

    @Test
    public void testSet() {
        Set<Book> set = person.getChineseBooks();
        System.out.println(set);
    }

    @Test
    public void testMap() {
        System.out.println(person.getMap());
    }
    @Test
    public void testArray() {
        System.out.println(Arrays.asList(person.getStrings()));
    }

    @Test
    public void testBean() {
        System.out.println(Arrays.asList(person.getBook()));
    }

}
