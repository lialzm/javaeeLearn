package com.lialzm.controller;

import com.lialzm.bean.City;
import com.lialzm.bean.Role;
import com.lialzm.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping("/getUserModel")
    @ResponseBody
    public String getUserModel(@ModelAttribute User user) {
        return user.toString();
    }


    @RequestMapping("/setSession")
    @ResponseBody
    public String setSession(Model model,
                             HttpSession session,
                             HttpServletRequest request) {
        City city = new City();
        city.setCityName("shanghai");
        model.addAttribute("city", city);
        session.setAttribute("city", city);
        System.out.println(request.getSession().getId());
        return city.toString();
    }

    @RequestMapping("/getSession")
    @ResponseBody
    public String getSession(HttpSession session,
                             HttpServletRequest request) {
        System.out.println(request.getSession().getId());
        System.out.println(session.getAttribute("city"));
        return new City().toString();
    }

    @RequestMapping("/clearSession")
    @ResponseBody
    public String clearSession(@ModelAttribute City city, SessionStatus status) {
        status.setComplete();
        return "success";
    }


}
