package com.lialzm.controller;

import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by A on 2017/3/24.
 */
@Controller
//这个注解要放在class上
@Validated
public class TestController2 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/test2")
    @ResponseStatus(HttpStatus.OK)
    public String test2(@Email @RequestParam  String email) {
        logger.info(email);
        return "";
    }

    @RequestMapping("/test3")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String test3(@Max(100) @Min(1) @RequestParam  Integer age) {
        return age.toString();
    }



}
