package com.management.staff.security.dto.changePassword;
import com.management.staff.global.utils.validators.PasswordValid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class ChangePasswordDto {
    @PasswordValid
    private String password;
    @PasswordValid
    private String confirmPassword;
    @NotBlank
    private String forgottenPassword;
}