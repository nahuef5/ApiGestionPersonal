package com.management.staff.security.dto;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter
public class LoginUserDto{
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}