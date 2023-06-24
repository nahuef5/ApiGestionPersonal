package com.management.staff.controllers;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.models.QueryPageable;
import com.management.staff.services.staffService.StaffServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(
            summary = "Get all staffs paginated by Position",
            description = "Traera todo el personal desde una paginacion. ADMIN, ADMINTRAINEE, EJECUTIVO."
    )
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_EJECUTIVO')")
    @GetMapping("allStaffs-ByPosition")
    public ResponseEntity<Page<Staff>> getAllByPosition(QueryPageable queryPageable){
        return ResponseEntity.ok(service.getAllStaffs(queryPageable));
    }
    @Operation(
            summary = "Get all staffs paginated by Area",
            description = "Traera todo el personal desde una paginacion. ADMIN, ADMINTRAINEE, EJECUTIVO."
    )
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_EJECUTIVO')")
    @GetMapping("allStaffs-ByArea")
    public ResponseEntity<Page<Staff>> getAllByArea(QueryPageable queryPageable){
        return ResponseEntity.ok(service.getAllStaffs(queryPageable));
    }
    //Get a staff
    @Operation(
            summary = "Get a staff by dni",
            description = "Solo podra acceder a esa informacion quien le pertenece, los admin y ejecutivos"
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("byDni/{dni}/")
    public ResponseEntity<StaffDto>getOne(@PathVariable ("dni") int dni)
            throws ResourceNotFoundException,Exception{
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getOneStaff(dni));  
    }
    //Update salary
    @Operation(
            summary = "Update staff salary",
            description = "Actualiza el salario de un staff pasando como pathvariable el dni. ADMINTRAINEE"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMINTRAINEE')")
    @PutMapping("update-salary/{dni}")
    public ResponseEntity<MessageHandler> setSalary(@PathVariable ("dni") int dni,
            @Valid @RequestBody GrossSalaryStaffDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStaffSalary(dto, dni));
    }
}