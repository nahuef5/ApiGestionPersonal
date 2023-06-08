package com.management.staff.security.dto.user;
import com.management.staff.security.dto.*;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter
public class LoginUserDto{
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}