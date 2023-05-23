package com.management.staff.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.management.staff.enums.AreaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;
import lombok.*;
//Esta entidad solo podra crearse desde los enums. No se podran eliminar o crear
//solo se podran actualizar los sueldos
@Entity
@Table(name="areas_empresa")
@Getter
@NoArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private short id_area;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AreaEnum area;
    
    @JsonManagedReference
    @OneToMany(mappedBy="area", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Staff> staff=new HashSet<>();
    
    private int grossSalary;
    private int netSalary;

    public Area(AreaEnum area) {
        this.area = area;
        this.grossSalary = 1;
        this.netSalary = 1;
    }
    public void setGrossSalary(int id_area,int grossSalary) {
        this.grossSalary = grossSalary;
    }
    public void setNetSalary(int id_area,int netSalry) {
        this.netSalary = netSalry;
    }

    public void setStaff(Set<Staff> staff) {
        this.staff = staff;
    }
}