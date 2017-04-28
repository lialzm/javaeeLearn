package com.catfish;

import com.catfish.bean.TestBean;
import com.catfish.dao.TestDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by A on 2017/4/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-applicationContext.xml"})
@WebAppConfiguration
@Rollback(value = false)
public class MyTest {

    @Autowired
    TestDao testDao;

//    @Transactional
    @Test
    public void testInsert() {
        testInsert2();
        long count = testDao.count();
        Assert.assertNotEquals(0, count);
    }

    @Transactional
    public void testInsert2() {
        TestBean testBean = new TestBean();
        testBean.setId(1);
        testDao.save(testBean);
    }


}
