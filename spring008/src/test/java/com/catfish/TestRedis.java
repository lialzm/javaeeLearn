package com.catfish;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by A on 2017/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-applicationContext.xml"})
@WebAppConfiguration
public class TestRedis {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> template;

    private ValueOperations<String, String> operations;
    private ListOperations<String, String> listOperations;


    @Before
    public void init() {
        operations = template.opsForValue();
        listOperations = template.opsForList();
    }

    @Test
    public void testString() {
        template.delete("tempkey");
        operations.set("tempkey", "tempValue");
        Assert.assertEquals("tempValue", operations.get("tempkey"));
    }

    @Test
    public void testList() {
        template.delete("list");
        listOperations.leftPush("list", "1");
        listOperations.leftPush("list", "2");
        System.out.println(listOperations.range("list", 0, -1));
    }


    @Test
    public void testPool() throws Exception {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                operations.set("pool1", "pool1");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                operations.set("pool1", "pool1");
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                operations.set("pool1", "pool1");
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            public void run() {
                operations.set("pool1", "pool1");
            }
        });
        Thread thread5 = new Thread(new Runnable() {
            public void run() {
                operations.set("pool1", "pool1");
                try {
                    Thread.sleep(20 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread6 = new Thread(new Runnable() {
            public void run() {
                operations.set("pool1", "pool1");
            }
        });
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }

}
