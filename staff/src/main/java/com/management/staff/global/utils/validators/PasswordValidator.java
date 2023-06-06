package com.management.staff.global.utils.validators;
import jakarta.validation.*;
public class PasswordValidator implements ConstraintValidator<PasswordValid, String>{
    private final String PASSWORD_VALIDATION="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d.*\\d)(?=.*[@#$&+_-])(?!.*\\s).{8,25}$";
    @Override
    public boolean isValid(String password, ConstraintValidatorContext cvc) {
        return password.matches(PASSWORD_VALIDATION);
    }
}