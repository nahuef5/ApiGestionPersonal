package com.management.staff.models;
import jakarta.validation.constraints.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
//clase modelo de direccion del staff
public class Address{
    @NotBlank
    @NotEmpty
    private String street;
    @Min(1)
    @Max(9999)
    private short num;
    @NotBlank
    @NotEmpty
    private String neighborhood;
    @NotBlank
    @NotEmpty
    private String city;
    private String province;
    private String country;
    public Address(String street, short num, String neighborhood, String city) {
        this.street = street;
        this.num = num;
        this.neighborhood = neighborhood;
        this.city = city;
    }
}