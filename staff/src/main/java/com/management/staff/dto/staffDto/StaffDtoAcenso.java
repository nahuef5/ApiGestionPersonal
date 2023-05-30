package com.management.staff.dto.staffDto;
import lombok.*;
import jakarta.validation.constraints.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class StaffDtoAcenso {
    @Min(1)
    @Max(6)
    private short id_position;
}