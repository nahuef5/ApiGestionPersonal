package com.management.staff.security.entities;
import com.management.staff.security.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="roles")
@NoArgsConstructor
@Getter @Setter
public class Role{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private short id_role;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    public Role(RoleEnum roleEnum){
        this.role=roleEnum;
    }
}