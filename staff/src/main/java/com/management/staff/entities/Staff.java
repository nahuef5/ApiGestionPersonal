package com.management.staff.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import lombok.*;

@Entity
@Table(name="Personal_Empresa")
@Getter
@NoArgsConstructor
//La idea es crear el trabajador desde el enlace del area, donde se pasara el
//puesto
public class Staff implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_staff;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    private String address;
    @NotNull
    private int dni;
    @NotNull
    private String born;//Por ahora lo pongo en string ya lo cambio a Date 
    @NotNull
    private String area;//despues creo la clase y objeto area
    @NotNull
    private String position;//despues creo la clase y objeto 
    @NotNull
    private double grossSalary;
    @NotNull
    private double netSalary;
    
    public Staff(String name, String surname, String address, int dni, String born, String area, String position) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dni = dni;
        this.born = born;
        this.area = area;
        this.position = position;
        this.grossSalary=900;
        this.netSalary=1000;
    }
    //Setters Adress, Area, Position, GrossSalary, NetSalary
    //Unico atributos que se pueden modificar
    public void setAddress(String address) {
        this.address = address;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }
    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }
}