package com.management.staff.entities;
import com.fasterxml.jackson.annotation.*;
import com.management.staff.enums.AreaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;
import lombok.*;
@Entity
@Getter
@NoArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private short id_area;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AreaEnum area;
    private float bonus;
    @OneToMany(mappedBy="area", cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonIgnoreProperties("areaName")
    private Set<Staff> staff=new HashSet<>();
    public Area(AreaEnum area) {
        this.area = area;
        this.bonus=0;
    }
    public void setBonus(float bonus){
        this.bonus = bonus;
    }
}