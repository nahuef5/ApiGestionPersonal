package com.management.staff.dto.staffDto;
import com.management.staff.global.utils.validators.ValidDate;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class StaffDto{
    @Size(min = 3, max = 15, message = "El nombre del personal debe tener como minimo 3 caracteres y maximo 15.")
    private String name;
    @Size(min = 3, max = 10, message = "El apellido del personal debe tener como minimo 3 caracteres y maximo 15.")
    private String surname;
    @NotBlank
    @NotEmpty
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{1,25}( [0-9]{1,5})?$")
    private String address;
    @Min(20000000)
    @Digits(integer=8, fraction=0)
    private int dni;
    //Implemento una validacion personalizada de fecha de nacimiento
    @ValidDate
    private LocalDate born;
    private String area;
    private String position;
    private double grossSalary;
    private double netSalary;
    //Para crear objetos de entidad
    public StaffDto(String name, String surname, String address, int dni, LocalDate born, String position) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dni = dni;
        this.born = born;
        this.position = position;
    }
}