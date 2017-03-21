package com.lialzm.controller;

import com.lialzm.bean.City;
import com.lialzm.bean.Role;
import com.lialzm.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * Created by A on 2017/3/17.
 */
@Controller
@SessionAttributes(value = {"city"})
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

    @RequestMapping(value = "/requestParamValue")
    @ResponseBody
    public String requestParamValue(@RequestParam(value = "name") String name1) {
        logger.info("name=" + name1);
        return name1;
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
        return user.toString();
    }

    @ModelAttribute("user")
    public User getUser() {
        User user = new User();
        user.setId("11");
        Role role = new Role();
        role.setId("123");
        user.setRole(role);
        return user;
    }


    @RequestMapping("/getRoleModel")
    @ResponseBody
    public String getRoleModel(@ModelAttribute Role user) {
        return user.toString();
    }


    @RequestMapping("/setSession")
    @ResponseBody
    public String setSession(
            HttpSession session) {
        City city = new City();
        city.setCityName("shanghai");
        session.setAttribute("city", city);
        return city.toString();
    }

    @RequestMapping("/getSession")
    @ResponseBody
    public String getSession(@ModelAttribute City city) {
        return city.toString();
    }

    @RequestMapping("/clearSession")
    @ResponseBody
    public String clearSession(SessionStatus status) {
        status.setComplete();
        return "success";
    }

    @RequestMapping("/getUserByJson")
    @ResponseBody
    public String getUserByJson(@RequestBody User user){
        return user.toString();
    }

    @RequestMapping("/getFile")
    @ResponseBody
    public String getFile(@RequestPart("image") MultipartFile file){
        System.out.println(file.getName()+","+file.getSize());
        return "success";
    }

}
