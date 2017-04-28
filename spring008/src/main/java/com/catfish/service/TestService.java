package com.catfish.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by A on 2017/4/24.
 */
@Service
public class TestService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String queryUser() {

        return "test";
    }

}
