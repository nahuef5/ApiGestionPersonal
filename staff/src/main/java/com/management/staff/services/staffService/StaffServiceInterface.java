package com.management.staff.services.staffService;

import com.management.staff.dto.staffDto.ViewStaffDto;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import java.util.*;

public interface StaffServiceInterface {
    //Traera todos con sueldo incluido
    List<Staff>getAllWithAllAttributtes()throws ListEmptyException;
    //Traera la lista del dto de vista
    Set<ViewStaffDto> getAllViewDto();
    //traer un staff con todos los datos
    Staff getOneWithAllAttributes(int dni)throws ResourceNotFoundException;
    //traer un staff sin todos los datos
    ViewStaffDto getOneWithoutSomeAttributes(int dni)throws ResourceNotFoundException;
}
