package com.lialzm.controller;

import com.lialzm.bean.User;
import com.lialzm.spring.support.JsonRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by A on 2017/3/17.
 */
@Controller
public class TestController {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping("/getUserByJson")
    @ResponseBody
    public String getUserByJson(@RequestBody User user) {
        return user.toString();
    }

    @RequestMapping("/aaa")
    @ResponseBody
    public String aaa(@RequestParam Long id) {
        return "";
    }

    @RequestMapping("/getUserByJson2")
    @ResponseBody
    public String getUserByJson2(@JsonRequest("$.id") Long id, @JsonRequest("$.name") String name) {
        return id + "," + name;
    }

    @RequestMapping("/getUserByJson3")
    @ResponseBody
    public String getUserByJson3(@JsonRequest Long id, @JsonRequest String name) {
        return id + "," + name;
    }




}
