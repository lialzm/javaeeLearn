package com.lialzm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by A on 2017/3/23.
 */
@Controller
public class TestController2 {



    @RequestMapping("/testDate2")
    @ResponseBody
    public String testDate2(@RequestParam Date date) {
        System.out.println(date);
        return "";
    }
}
