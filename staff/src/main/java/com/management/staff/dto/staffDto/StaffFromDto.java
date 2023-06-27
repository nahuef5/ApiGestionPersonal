package com.management.staff.dto.staffDto;
import java.time.LocalDate;
import lombok.*;
@NoArgsConstructor
@Data
public class StaffFromDto{
    private String name;
    private String surname;
    private String fullAddress;
    private int dni;
    private LocalDate born;
    private String area;
    private String position;
    private LocalDate contractStart;
    private String email;
    private double basicSalary;
    private double grossSalary;
    private double netSalary;
    public StaffFromDto(String name, String surname, String fullAddress, int dni, LocalDate born, String area, String position, LocalDate contractStart, String email, double basicSalary, double grossSalary, double netSalary) {
        this.name = name;
        this.surname = surname;
        this.fullAddress = fullAddress;
        this.dni = dni;
        this.born = born;
        this.area = area;
        this.position = position;
        this.contractStart = contractStart;
        this.email = email;
        this.basicSalary = basicSalary;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
    }
}