package com.catfish.dao;

import com.catfish.bean.TestBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by A on 2017/4/19.
 */
@Repository
public interface TestDao extends JpaRepository<TestBean,Integer> {



}
