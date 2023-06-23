package com.management.staff.dto.areaDto;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AreaDto {
    private short id_area;
    private String area;
    private int allStaff;
    private double totalBasicSalarys;
    private double totalGrossSalary;
}