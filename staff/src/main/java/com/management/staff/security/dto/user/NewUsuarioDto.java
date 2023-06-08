package com.management.staff.security.dto.user;
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
    @Size(min = 6, max = 20)
    private String username;
    //Validacion creada
    @PasswordValid
    private String password;
    @PasswordValid
    private String passwordConfirm;

    public NewUsuarioDto(int dni, String email,String username, String password) {
        this.dni = dni;
        this.email = email;
        this.username=username;
        this.password = password;
    }
}