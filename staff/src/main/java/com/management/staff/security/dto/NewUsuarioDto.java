package com.management.staff.security.dto;
import com.management.staff.global.utils.validators.PasswordValid;
import jakarta.validation.constraints.*;
import lombok.*;
@NoArgsConstructor
@Getter@Setter
public class NewUsuarioDto {
    @Min(20000000)
    @Digits(integer=8, fraction=0)
    private int dni;
    @Email
    private String email;
    //Validacion creada
    @PasswordValid
    private String password;
    @PasswordValid
    private String passwordConfirm;

    public NewUsuarioDto(int dni, String email, String password) {
        this.dni = dni;
        this.email = email;
        this.password = password;
    }
}