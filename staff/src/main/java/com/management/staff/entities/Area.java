package com.management.staff.entities;
import com.fasterxml.jackson.annotation.*;
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
    
    @OneToMany(mappedBy="area", cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonIgnoreProperties("areaName")
    private Set<Staff> staff=new HashSet<>();
    
    private float budget;

    public Area(AreaEnum area) {
        this.area = area;
        this.budget=1000;
    }
    public void setBudget(float budget) {
        this.budget = budget;
    }
}