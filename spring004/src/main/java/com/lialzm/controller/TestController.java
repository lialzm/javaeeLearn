package com.lialzm.controller;

import com.lialzm.bean.User;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * Created by A on 2017/3/22.
 */

@RestController
@Validated
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/test")
    @ResponseBody
    public String test(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(Arrays.asList(bindingResult.getAllErrors().get(0).getArguments()).toString());
            ObjectError objectError = bindingResult.getAllErrors().get(0);
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                logger.info(fieldError.getField() + fieldError.getDefaultMessage());
            }
        }
        logger.info(user.toString());
        return "";
    }


    @RequestMapping("/test2")
    @ResponseStatus(HttpStatus.OK)
    public String test2(@Email @RequestParam("id") String id) {
        logger.info(id);
        return "";
    }
}
