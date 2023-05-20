package com.management.staff.controllers;
import com.management.staff.dto.staffDto.ViewStaffDto;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.ListEmptyException;
import com.management.staff.global.exceptions.ResourceNotFoundException;
import com.management.staff.services.staffService.StaffServiceImpl;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onlystaff/")
@CrossOrigin("http://localhost:5000/")
//Desde este controlador solo se podrá traer el objeto staff desde
//diferentes puntos de vista
public class StaffController{
    @Autowired
    StaffServiceImpl service;
    //Get con todos los atributos
    @GetMapping("all/")
    public ResponseEntity<List<Staff>> getAllStaff() throws ListEmptyException{
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllWithAllAttributtes());
    }
    //Get todos con algunso atributos
    @GetMapping("getAll/")
    public ResponseEntity<Set<ViewStaffDto>> getAllViewStaffDto() throws ListEmptyException{
        //Set<ViewStaffDto>listDto=service.getAllViewDto();
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllViewDto());
    }
    //Get un staff con todos los atributos
    @GetMapping("staff/{dni}/")
    public ResponseEntity<Staff> getOneStaff(@PathVariable ("dni") int dni) throws ResourceNotFoundException{
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getOneWithAllAttributes(dni));
    }
    //Get un staff solo con alfunos atributos
    @GetMapping("getStaff/{dni}/")
    public ResponseEntity<ViewStaffDto> getAllStaff(@PathVariable ("dni") int dni) throws ResourceNotFoundException  {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getOneWithoutSomeAttributes(dni));
    }
}