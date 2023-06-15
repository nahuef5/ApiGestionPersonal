package com.management.staff.controllers;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.models.QueryPageable;
import com.management.staff.services.staffService.StaffServiceImpl;
import jakarta.validation.Valid;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/staff/")
@CrossOrigin(origins="http://localhost:5000/")
//Desde este controlador solo se podrá traer el objeto staff desde
//diferentes puntos de vista de seguridad
public class StaffController{
    @Autowired
    StaffServiceImpl service;
    //Get lista (autenticado)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USUARIO')")
    @GetMapping("all/")
    public ResponseEntity<List<Staff>> getListaAutenticado() throws ListEmptyException{
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getListaAutenticado());
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USUARIO')")
    @GetMapping("/pagination")
    public ResponseEntity<Page<Staff>> getAll(QueryPageable queryPageable){
        return ResponseEntity.ok(service.getAllStaffs(queryPageable));
    }
    //Get ilsta (universal)
    @GetMapping("")
    public ResponseEntity<Set<AnyoneReadsStaffDto>> getListaUniversal() throws ListEmptyException{
        //Set<ViewStaffDto>listDto=service.getAllViewDto();
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getListaUniversal());
    }
    //Get 1 (autenticado)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USUARIO')")
    @GetMapping("one/{dni}/")
    public ResponseEntity<StaffDto> getIndividualAutenticado(@PathVariable ("dni") int dni) throws ResourceNotFoundException{
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getIndividualAutenticado(dni));
    }
    //Get 1 (universal)
    @GetMapping("getOne/{dni}/")
    public ResponseEntity<AnyoneReadsStaffDto> getIndividualUniversal(@PathVariable ("dni") int dni) throws ResourceNotFoundException  {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getIndividualUniversal(dni));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINTRAINEE')")
    @PutMapping("update-salary/{dni}")
    public ResponseEntity<MessageHandler> setSalary(@PathVariable ("dni") int dni,@Valid @RequestBody GrossSalaryStaffDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStaffSalary(dto, dni));
    }
}