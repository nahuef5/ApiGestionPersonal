package com.management.staff.dto.positionDto;
import jakarta.validation.constraints.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PositionDto{
    @Min(1000)
    @Max(1000000)
    private float grossSalary;
    @Min(1000)
    @Max(1000000)
    private float netSalary;
}
