package com.management.staff.entities;
import com.fasterxml.jackson.annotation.*;
import com.management.staff.models.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="personal_empresa")
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
    private String name;
    @NotNull
    private String surname;
    @Transient
    private Address address;
    @NotNull
    private String addressCoordinates;
    @NotNull
    private int dni;
    @NotNull
    private LocalDate born;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_area")
    @JsonIgnore
    private Area area;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_position")
    @JsonIgnore
    private Position position;
    
    @NotNull
    private LocalDate contractStart;
    @NotNull
    private String email;
    @NotNull
    private double basicSalary;
    @NotNull
    private double grossSalary;
    @NotNull
    private double netSalary;
 
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
    public Staff(String name, String surname, Address address, int dni, LocalDate born, Area area, Position position, LocalDate contractStart, String email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dni = dni;
        this.born = born;
        this.area = area;
        this.position = position;
        this.contractStart=contractStart;
        this.email=email;
        this.basicSalary=position.getBasicSalary();
    }
    //Unicos atributos que se pueden modificar
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setAddressCoordinates(String addressCoordinates) {
        this.addressCoordinates = addressCoordinates;
    }
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