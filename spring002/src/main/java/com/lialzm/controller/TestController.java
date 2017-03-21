package com.lialzm.controller;

import com.lialzm.spring.support.JsonRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by A on 2017/3/17.
 */
@Controller
public class TestController {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping("/getUserByJson")
    @ResponseBody
    public String getUserByJson(@JsonRequest("$.id") String id) {
        return id;
    }


}
