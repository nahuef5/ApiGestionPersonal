package com.management.staff.global.utils.validators;
import jakarta.validation.*;
import java.lang.annotation.*;
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValid{
    String message() default
        "The password must contain at least one uppercase letter, one lowercase letter, at least two numbers, some special character like '@#$&+_-', a minimum of 8 characters and a maximum of 20.";
    Class<?>[]groups()default{};
    Class<? extends Payload>[] payload() default{};
}