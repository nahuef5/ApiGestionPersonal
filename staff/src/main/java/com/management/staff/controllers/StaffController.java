package com.management.staff.controllers;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import com.management.staff.services.staffService.StaffServiceImpl;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/onlystaff/")
@CrossOrigin(origins="http://localhost:5000/")
//Desde este controlador solo se podrá traer el objeto staff desde
//diferentes puntos de vista de seguridad
public class StaffController{
    @Autowired
    StaffServiceImpl service;
    //Get lista (autenticado)
    @GetMapping("all/")
    public ResponseEntity<List<Staff>> getListaAutenticado() throws ListEmptyException{
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getListaAutenticado());
    }
    //Get ilsta (universal)
    @GetMapping("getAll/")
    public ResponseEntity<Set<AnyoneReadsStaffDto>> getListaUniversal() throws ListEmptyException{
        //Set<ViewStaffDto>listDto=service.getAllViewDto();
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getListaUniversal());
    }
    //Get 1 (autenticado)
    @GetMapping("staff/{dni}/")
    public ResponseEntity<StaffDto> getIndividualAutenticado(@PathVariable ("dni") int dni) throws ResourceNotFoundException{
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getIndividualAutenticado(dni));
    }
    //Get 1 (universal)
    @GetMapping("getStaff/{dni}/")
    public ResponseEntity<AnyoneReadsStaffDto> getIndividualUniversal(@PathVariable ("dni") int dni) throws ResourceNotFoundException  {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getIndividualUniversal(dni));
    }
}