package com.lialzm.controller;

import com.lialzm.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by A on 2017/3/22.
 */

@RestController
//@Validated
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping("/test")
    @ResponseBody
    public String test(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ObjectError objectError = bindingResult.getAllErrors().get(0);
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                logger.info(fieldError.getField() + fieldError.getDefaultMessage());
            }
        }
        logger.info(user.toString());
        return "";
    }

    @RequestMapping("/testGroup")
    @ResponseBody
    public String testGroup(){

        return "";
    }



}
