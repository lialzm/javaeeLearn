package com.catfish.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by A on 2017/4/24.
 */
@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Cacheable("test")
    @ResponseBody
    @RequestMapping("/test")
    public String test1() {
        logger.info("------------------");
        return "test";
    }



}
