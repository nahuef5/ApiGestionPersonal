package com.management.staff.dto.staffDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewStaffDto {
    private String name;
    private String surname;
    private String born;
    private String area;
    private String position;
}
