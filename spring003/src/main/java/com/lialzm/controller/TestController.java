package com.lialzm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by A on 2017/3/22.
 */
@Controller
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestParam Long id) {
        logger.info(id.toString());
        return "";
    }


    @InitBinder
//此处的参数也可以是ServletRequestDataBinder类型
    public void initBinder(WebDataBinder binder) throws Exception {
        //注册自定义的属性编辑器
        //1、日期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        //表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
        binder.registerCustomEditor(Date.class, dateEditor);
        //自定义的电话号码编辑器(和【4.16.1、数据类型转换】一样)
    }

    @RequestMapping("/testDate")
    @ResponseBody
    public String testDate(@RequestParam Date id) {
        logger.info(id.toString());
        return "";
    }


}
