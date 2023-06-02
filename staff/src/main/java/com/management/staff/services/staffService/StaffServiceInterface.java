package com.management.staff.services.staffService;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import com.management.staff.models.QueryPageable;
import java.util.*;
import org.springframework.data.domain.*;

public interface StaffServiceInterface {
    //Pagination
    Page<Staff> getAllStaffs(QueryPageable queryPageable)throws ListEmptyException;
    //Traera lista con todos los empleados
    List<Staff>getListaAutenticado()throws ListEmptyException;
    //Traera la lista del dto de vista
    Set<AnyoneReadsStaffDto> getListaUniversal()throws ListEmptyException;
    //traer un staff con todos los datos
    StaffDto getIndividualAutenticado(int dni)throws ResourceNotFoundException;
    //traer un staff sin todos los datos
    AnyoneReadsStaffDto getIndividualUniversal(int dni)throws ResourceNotFoundException;
}
