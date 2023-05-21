package com.management.staff.entities;
import com.management.staff.global.utils.validators.ValidDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Personal_Empresa")
@Getter
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
    @Size(min = 3, max = 10, message = "Staff's name must be between 3 and 10 characters")
    private String name;
    @NotNull
    @Size(min = 3, max = 10, message = "Staff's surname must be between 3 and 10 characters")
    private String surname;
    @NotNull
    @NotEmpty
    @NotBlank
    @Pattern(regexp="^[a-zA-Z]{1,13} [1-9]\\d{0,3}$")
    private String address;
    @NotNull
    @Min(20000000)
    @Digits(integer=8, fraction=0)
    private int dni;
    //Implemento una validacion personalizada de fecha de nacimiento
    @ValidDate
    private LocalDate born; 
    @NotNull
    private String area;//despues creo la clase y objeto area
    @NotNull
    private String position;//despues creo la clase y objeto 
    @NotNull
    private double grossSalary;
    @NotNull
    private double netSalary;
    
    public Staff(String name, String surname, String address, int dni, LocalDate born, String area, String position) {
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