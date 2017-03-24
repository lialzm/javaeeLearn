package com.lialzm.constraint;

import com.lialzm.annotation.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by A on 2017/3/24.
 */
public class StatusValidator implements ConstraintValidator<Status, String> {
    private final String[] ALL_STATUS = {"created", "paid", "shipped", "closed"};

    public void initialize(Status status) {

    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Arrays.asList(ALL_STATUS).contains(value)) {
            return true;
        }
        return false;
    }
}
