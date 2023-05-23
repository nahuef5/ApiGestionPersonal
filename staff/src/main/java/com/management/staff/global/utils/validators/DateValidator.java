package com.management.staff.global.utils.validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<ValidDate, LocalDate>{    
    private int minYear;
    private int maxYear;
    //variable que se volvera true luego del @Scheduled
    public static boolean updatedLocalDate = false;
    
    //Modficador de año maximo y minimo
    private void updateDateLimit(){
        LocalDate present = LocalDate.now();
        minYear = present.getYear()-65;
        maxYear= present.getYear()-18;
    }

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        this.minYear=constraintAnnotation.minYear();
        this.maxYear=constraintAnnotation.maxYear();
    }
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
        if(localDate == null)
            return false;
        //Si se cumple el año usamos metodo actualizador de min y max
        //y volvemos false la varible updatedLocalDate
        if(updatedLocalDate){
            updateDateLimit();
            updatedLocalDate=false;
        }
        LocalDate minLocalDate= LocalDate.of(minYear, 1, 1);
        LocalDate maxLocalDate= LocalDate.of(maxYear, 12, 31);
        
        return localDate.isAfter(minLocalDate) && localDate.isBefore(maxLocalDate);
    }   
}