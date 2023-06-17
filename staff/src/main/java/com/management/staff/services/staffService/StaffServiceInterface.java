package com.management.staff.services.staffService;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.models.QueryPageable;
import org.springframework.data.domain.*;

public interface StaffServiceInterface{
    //Pagination con todo staff
    Page<Staff> getAllStaffs(QueryPageable queryPageable)throws ListEmptyException;
    //traer un staff con todos los datos
    StaffDto getOneStaff(int dni)throws ResourceNotFoundException, Exception;
    //modificar sueldo bruto y neto desde Dto se pasara dni para modificar por staff
    void setGrossSalary(GrossSalaryStaffDto dto, int dni);
    void setNetSalary(GrossSalaryStaffDto dto, int dni);
    MessageHandler updateStaffSalary(GrossSalaryStaffDto dto, int dni)throws ResourceNotFoundException;
}
