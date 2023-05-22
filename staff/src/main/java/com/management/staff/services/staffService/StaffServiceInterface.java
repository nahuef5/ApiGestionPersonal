package com.management.staff.services.staffService;

import com.management.staff.dto.staffDto.StaffDto;
import com.management.staff.dto.staffDto.AnyoneReadsStaffDto;
import com.management.staff.global.exceptions.*;
import java.util.*;

public interface StaffServiceInterface {
    //Traera lista con todos los empleados
    Set<StaffDto>getListaAutenticado()throws ListEmptyException;
    //Traera la lista del dto de vista
    Set<AnyoneReadsStaffDto> getListaUniversal()throws ListEmptyException;
    //traer un staff con todos los datos
    StaffDto getIndividualAutenticado(int dni)throws ResourceNotFoundException;
    //traer un staff sin todos los datos
    AnyoneReadsStaffDto getIndividualUniversal(int dni)throws ResourceNotFoundException;
}
