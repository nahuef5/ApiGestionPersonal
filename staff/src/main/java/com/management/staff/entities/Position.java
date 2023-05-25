package com.management.staff.entities;
import com.management.staff.enums.PositionEnum;
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
    private Set<String> staff= new HashSet<>();
    @NotNull
    private float grossSalary;
    @NotNull
    private float netSalary;

    public Position(PositionEnum position) {
        this.position = position;
        this.grossSalary=1;
        this.netSalary=1;
        staff.add("Trabajador1");
        staff.add("Trabajador2");
        staff.add("Trabajador3");
    }

    public void setStaff(Set<String> staff) {
        this.staff = staff;//corroborar si no es mejor add...
    }
    public void setGrossSalary(float grossSalary) {
        this.grossSalary = grossSalary;
    }
    public void setNetSalary(float netSalary) {
        this.netSalary = netSalary;
    }    
}