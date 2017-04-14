package com.lialzm.annotation;

import com.lialzm.constraint.UserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by apple on 17/4/6.
 */
@Constraint(validatedBy = {UserValidator.class})
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUser {

    String message() default "错误的用户id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
