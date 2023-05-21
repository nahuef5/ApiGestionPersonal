package com.management.staff.dto.staffDto;
import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewStaffDto {
    private String name;
    private String surname;
    private LocalDate born;
    private String area;
    private String position;
}
