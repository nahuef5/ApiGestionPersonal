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
    @Size(min = 3, max = 10, message = "Staff's name must be between 3 and 10 characters")
    private String name;
    @Size(min = 3, max = 10, message = "Staff's surname must be between 3 and 10 characters")
    private String surname;
    @NotBlank
    @NotEmpty
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{1,25}( [0-9]{1,5})?$", message="It must contain at least one word and a numbering.")
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