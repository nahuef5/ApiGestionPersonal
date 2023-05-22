package com.management.staff.global.utils.validators;
import jakarta.validation.*;
import java.lang.annotation.*;

//Interfaz para generar mi validcion de fecha personalizada
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateValidator.class)
public @interface ValidDate{
    int minYear() default 1958;
    int maxYear() default 2005;
    
    String message() default "The field date is not correct.";

    Class<?>[]groups() default{};
    Class<? extends Payload>[] payload() default {};
}