package com.management.staff.entities;
import com.fasterxml.jackson.annotation.*;
import com.management.staff.models.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Getter
@NoArgsConstructor
public class Staff extends Person{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_staff", columnDefinition = "VARCHAR(255)")
    private String id_staff;
//Area
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_area")
    @JsonIgnore
    private Area area;
//Position
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_position")
    @JsonIgnore
    private Position position;
    @NotNull
    private LocalDate contractStart;
    @NotNull
    private double basicSalary;
    @NotNull
    private double grossSalary;
    @NotNull
    private double netSalary;
    
    public Staff(Area area, Position position, LocalDate contractStart, String name, String surname, Address address, int dni, LocalDate born, String email) {
        super(name, surname, address, dni, born, email);
        this.area = area;
        this.position = position;
        this.contractStart = contractStart;
        this.basicSalary=position.getBasicSalary();
        
    }
    //Unicos atributos que se pueden modificar
    public void setPosition(Position position) {
        this.position = position;
    }
    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }
    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }
    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }
}