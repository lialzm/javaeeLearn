package com.catfish.controller;

import com.catfish.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by A on 2017/4/1.
 */
@Controller
public class TestController {


    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
        Cookie cookie = new Cookie("name", "1");
        cookie.setMaxAge(10);
        response.addCookie(cookie);
        httpSession.setAttribute("user", getUser());
        return "111";
    }

    //    @ModelAttribute
    public User getUser() {
        User user = new User();
        user.setId("123");
        return user;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest request, @SessionAttribute User user, @CookieValue String name) {
        System.out.println(request.getSession(false).getId());
        System.out.println(user);
        System.out.println(name);
        return "test";
    }

}
