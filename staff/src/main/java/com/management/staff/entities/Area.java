package com.management.staff.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.management.staff.enums.AreaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;
import lombok.*;

@Entity
@Table(name="areas_empresa")
@Getter @Setter
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
    private int netSalry;

    public Area(AreaEnum area) {
        this.area = area;
        this.grossSalary = 5000;
        this.netSalry = 6000;
    }
}