package com.lialzm.controller;

import com.lialzm.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Created by A on 2017/3/17.
 */
@Controller
public class TestController {


    private Logger logger = LoggerFactory.getLogger(getClass());

    public TestController() {
        logger.info("TestControllerTest");
    }


    @RequestMapping(value = "/form")
    @ResponseBody
    public String form(String name, Integer age) {
        logger.info("name=" + name + "," + "age=" + age);
        return name + "," + age;
    }

    @RequestMapping(value = "/form2")
    @ResponseBody
    public String form2(@RequestParam String name) {
        logger.info("name=" + name);
        return name;
    }

    @RequestMapping(value = "/names")
    @ResponseBody
    public String names(@RequestParam String[] name) {
        logger.info("name=" + Arrays.asList(name));
        return Arrays.asList(name).toString();
    }

    @RequestMapping(value = "/form3")
    @ResponseBody
    public String form3(@RequestParam(defaultValue = "li") String name) {
        logger.info("name=" + name);
        return name;
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public String getUser(@PathVariable Long id) {
        return String.valueOf(id);
    }

    @RequestMapping("/getCookie")
    @ResponseBody
    public String getCookie(@CookieValue("JSESSIONID") String sessionId) {
        return sessionId;
    }

    @RequestMapping("/getHead")
    @ResponseBody
    public String getHead(@RequestHeader String userId) {
        return userId;
    }

    @RequestMapping("/getUserByModel")
    @ResponseBody
    public String getUserByModel(@ModelAttribute("user") User user) {
        logger.info(user.toString());
        return "";
    }


}
