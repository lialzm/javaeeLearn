package com.lialzm.constraint;

import com.lialzm.annotation.CheckUser;
import com.lialzm.bean.User;
import com.lialzm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by apple on 17/4/6.
 */
public class UserValidator implements ConstraintValidator<CheckUser, User> {


    @Autowired
    UserService userService;

    public void initialize(CheckUser constraintAnnotation) {

    }

    public boolean isValid(User user, ConstraintValidatorContext context) {
        return userService.checkUser(user);
    }
}
