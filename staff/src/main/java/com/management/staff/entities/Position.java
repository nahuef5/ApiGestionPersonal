package com.management.staff.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.management.staff.enums.PositionEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;
import lombok.*;
@Entity
@Table(name="puestos_empresa")
@NoArgsConstructor
@Getter
//@JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
public class Position {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private short id_position;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PositionEnum position;
    @NotNull
    @OneToMany(mappedBy="position", cascade= CascadeType.ALL, orphanRemoval=true)
    //@JsonManagedReference
    @JsonIgnoreProperties("positionName")
    private Set<Staff> staff= new HashSet<>();
    @NotNull
    private float grossSalary;
    @NotNull
    private float netSalary;

    public Position(PositionEnum position) {
        this.position = position;
        this.grossSalary=1000;
        this.netSalary=1000;
    }
    public void setGrossSalary(float grossSalary) {
        this.grossSalary = grossSalary;
    }
    public void setNetSalary(float netSalary) {
        this.netSalary = netSalary;
    }
    public void addStaff(Staff staff){
        this.staff.add(staff);
    }
}