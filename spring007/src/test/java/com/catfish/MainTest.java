package com.catfish;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

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
    public void testSet() {
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

}
