package com.lialzm.controller;

import com.lialzm.bean.User;
import com.lialzm.constraint.Group1;
import com.lialzm.constraint.Group2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;

/**
 * Created by A on 2017/3/22.
 */

@Controller
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
    public User testGroup(@ModelAttribute @Validated(value = {Default.class,Group1.class, Group2.class}) User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list
                    ) {
                logger.info(error.getDefaultMessage());
            }
            logger.info("error");
            return null;
        }
        return user;
    }




}
