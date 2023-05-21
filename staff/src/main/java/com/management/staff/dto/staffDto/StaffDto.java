package com.management.staff.dto.staffDto;
import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StaffDto {
    private String name;
    private String surname;
    private String address; 
    private int dni; 
    private LocalDate born;
    private String area;
    private String position;

    public void setAddress(String address) {
        this.address = address;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public void setPosition(String position) {
        this.position = position;
    }
}