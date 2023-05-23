package com.management.staff.dto.staffDto;
import lombok.*;
import jakarta.validation.constraints.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class StaffDtoPatch {
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{1,25}( [0-9]{1,5})?$", message="It must contain at least one word and a numbering.")
    private String address;
    @NotNull
    private String position;
}
