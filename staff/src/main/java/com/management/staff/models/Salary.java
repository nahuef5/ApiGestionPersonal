package com.management.staff.models;
public class Salary {
    //atributos que suman valor al salario
    public static final float ANTIQUITY=0.01f;
    public static final float PRESENTEEISM=0.1f;
    
    public static final float INSSJP=0.03f;
    public static final float PENSION=0.11f;
    public static final float OBRA_SOCIAL=0.03f;
    public static final float SINDICATO=0.02f;

    public static double valueExtraHours(double basicSalary){
        return (basicSalary/160)*1.5;
    }
}