package com.management.staff.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.management.staff.global.utils.validators.ValidDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="personal_empresa")
@Getter@Setter
@NoArgsConstructor
//La idea es crear el trabajador desde el enlace del area, donde se pasara el
//puesto
public class Staff{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_staff", columnDefinition = "VARCHAR(255)")
    private String id_staff;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String address;
    @NotNull
    private int dni;
    @NotNull
    private LocalDate born;
    
    @NotNull
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_area")
    private Area area;
    
    @NotNull
    private String position; 
    @NotNull
    private double grossSalary;
    @NotNull
    private double netSalary;
    
    public Staff(String name, String surname, String address, int dni, LocalDate born, Area area, String position) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dni = dni;
        this.born = born;
        this.area = area;
        this.position = position;
        this.grossSalary=area.getGrossSalary();
        this.netSalary=area.getNetSalary();
    }
    //Setters Adress, Area, Position, GrossSalary, NetSalary
    //Unico atributos que se pueden modificar
    public void setAddress(String address) {
        this.address = address;
    }
    public void setArea(Area area) {
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