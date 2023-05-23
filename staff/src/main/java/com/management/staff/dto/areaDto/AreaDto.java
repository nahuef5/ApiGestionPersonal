package com.management.staff.dto.areaDto;
import jakarta.validation.constraints.*;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AreaDto {
    @Min(1000)
    @Max(100000)
    private int grossSalary;
    @Min(1000)
    @Max(100000)
    private int netSalary;
}
