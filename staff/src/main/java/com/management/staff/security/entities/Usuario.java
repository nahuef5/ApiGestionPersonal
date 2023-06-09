package com.management.staff.security.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;
import lombok.*;
@Entity
@Table(name="usuarios")
@NoArgsConstructor
@Getter @Setter
public class Usuario {
    @Id
    @NotNull
    @Column(unique=true)
    private int dni;
    @NotNull
    @Column(unique=true)
    private String email;
    @Column(unique=true)
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String forgottenPassword;
    @NotNull
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="rol_user",
            joinColumns=@JoinColumn(name="dni"),
            inverseJoinColumns=@JoinColumn(name="id_rol")
    )
    private List<Role> roles= new ArrayList<>();

    public Usuario(int dni, String email,String username, String password) {
        this.dni = dni;
        this.email = email;
        this.username=username;
        this.password = password;
    }
}