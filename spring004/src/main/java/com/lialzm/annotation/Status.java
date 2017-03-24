package com.lialzm.annotation;

import com.lialzm.constraint.StatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by A on 2017/3/24.
 */
@Constraint(validatedBy = {StatusValidator.class})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Status {
    String message() default "不正确的状态 , 应该是 'created', 'paid', shipped', closed'其中之一";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
