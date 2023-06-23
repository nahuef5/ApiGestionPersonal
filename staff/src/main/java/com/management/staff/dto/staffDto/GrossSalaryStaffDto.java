package com.management.staff.dto.staffDto;
import jakarta.validation.constraints.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrossSalaryStaffDto{
    private boolean presenteeism;
    private boolean afiliado;
    @Min(0)
    @Max(20)
    private short quantityExtraHours;
}