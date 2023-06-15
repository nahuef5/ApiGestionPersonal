package com.management.staff.entities;
import com.fasterxml.jackson.annotation.*;
import com.management.staff.enums.PositionEnum;
import com.management.staff.global.exceptions.BusinesException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;
import lombok.*;
@Entity
@Table(name="puestos_empresa")
@NoArgsConstructor
@Getter
public class Position {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private short id_position;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PositionEnum position;
    @NotNull
    @OneToMany(mappedBy="position", cascade= CascadeType.ALL, orphanRemoval=true)
    @JsonIgnoreProperties("positionName")
    private Set<Staff> staff= new HashSet<>();
    @NotNull
    private double basicSalary;
    public Position(PositionEnum position) {
        this.position = position;
        this.basicSalary=1000;
    }
    public void setBasicSalary(double basicSalary)throws BusinesException{
        double min=this.basicSalary;
        if(basicSalary<min){
            throw new BusinesException
        ("El valor del sueldo basico que intenta actualizar no puede ser menor que el anterior: "+min);
        }
        this.basicSalary=basicSalary;
    }
    public void addStaff(Staff staff){
        this.staff.add(staff);
    }
}