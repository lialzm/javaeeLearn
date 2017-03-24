package com.lialzm;

import org.hibernate.validator.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by A on 2017/3/24.
 */
@Controller
@Validated
public class TestController {

    @RequestMapping("/test2")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String test2(@Email @RequestParam("id") String id) {
        return "";
    }

}
