package com.management.staff.dto.positionDto;
import jakarta.validation.constraints.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PositionDto{
    private String name;
    @Min(1000)
    @Max(1000000)
    private double basicSalary;
    public PositionDto(float basicSalary){
        this.basicSalary=basicSalary;
    }
}
