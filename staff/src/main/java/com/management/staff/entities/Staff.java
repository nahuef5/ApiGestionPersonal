package com.management.staff.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @ManyToOne
    @JoinColumn(name="id_area")
    @JsonIgnore
    //@JsonBackReference("area")
    private Area area;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_position")
    @JsonIgnore
    //@JsonBackReference("position")
    private Position position; 

    @NotNull
    private float grossSalary;
    @NotNull
    private float netSalary;
    
    @JsonProperty("positionName")
    public String getPositionName(){
        if (position !=null)
            return position.getPosition().name();
        return null;
    }
    @JsonProperty("areaName")
    public String getAreaName(){
        if (area !=null)
            return area.getArea().name();
        return null;
    }
    
    public Staff(String name, String surname, String address, int dni, LocalDate born, Area area, Position position) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dni = dni;
        this.born = born;
        this.area = area;
        this.position = position;
        this.grossSalary=position.getGrossSalary();
        this.netSalary=position.getNetSalary();
    }
    //Setters Adress, Area, Position, GrossSalary, NetSalary
    //Unico atributos que se pueden modificar
    public void setAddress(String address) {
        this.address = address;
    }
    public void setArea(Area area) {
        this.area = area;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public void setGrossSalary(float grossSalary) {
        this.grossSalary = grossSalary;
    }
    public void setNetSalary(float netSalary) {
        this.netSalary = netSalary;
    }
}