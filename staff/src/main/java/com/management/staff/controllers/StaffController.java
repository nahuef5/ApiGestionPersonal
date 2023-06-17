package com.management.staff.controllers;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.models.QueryPageable;
import com.management.staff.services.staffService.StaffServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/staff/")
@CrossOrigin(origins="http://localhost:5000/")
public class StaffController{
    @Autowired
    StaffServiceImpl service;
    //Get all staff
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_EJECUTIVO')")
    @GetMapping("allByPagination")
    public ResponseEntity<Page<Staff>> getAll(QueryPageable queryPageable){
        return ResponseEntity.ok(service.getAllStaffs(queryPageable));
    }
    
    //Get a staff
    @PreAuthorize("isAuthenticated()")
    //@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USUARIO')")
    @GetMapping("byDni/{dni}/")
    public ResponseEntity<StaffDto> getIndividualAutenticado(@PathVariable ("dni") int dni) throws ResourceNotFoundException, Exception{
        
            return ResponseEntity.status(HttpStatus.FOUND).body(service.getOneStaff(dni));
    
        
    }
    //Update salary
    @PreAuthorize("hasAuthority('ROLE_ADMINTRAINEE')")
    @PutMapping("update-salary/{dni}")
    public ResponseEntity<MessageHandler> setSalary(@PathVariable ("dni") int dni,@Valid @RequestBody GrossSalaryStaffDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStaffSalary(dto, dni));
    }
}