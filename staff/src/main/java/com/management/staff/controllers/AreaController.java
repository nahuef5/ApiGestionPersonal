package com.management.staff.controllers;
import com.management.staff.dto.areaDto.AreaDto;
import com.management.staff.dto.staffDto.StaffDto;
import com.management.staff.dto.staffDto.StaffDtoPatch;
import com.management.staff.entities.Area;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.*;
import com.management.staff.global.utils.validators.DateValidator;
import com.management.staff.services.areaService.AreaServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/byArea/")
@CrossOrigin(origins="http://localhost:5000/")
public class AreaController{
    @Autowired
    private AreaServiceImpl service;
    
//Ejecutar actualizacion de rango de fecha cada PRIMERO de ENERO
    @Scheduled(cron = "0 0 0 1 1 *") 
    public void actualizarLimitesFecha() {
        DateValidator.updatedLocalDate = true;
    }
//STAFF
    @PostMapping("{id_area}/")
    public ResponseEntity<MessageHandler>saveStaff
        (@PathVariable("id_area")short id_area, @Valid @RequestBody StaffDto dto)
                throws ResourceNotFoundException, BusinesException{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveStaff(id_area, dto));
    }
    @PutMapping("update/{dni}/")
    public ResponseEntity<MessageHandler>updateStaff(@PathVariable("dni")int dni,@Valid @RequestBody StaffDtoPatch dto){
        return ResponseEntity.ok().body(service.updateStaff(dni, dto));
    }
    @DeleteMapping("delete-staff/{dni}/")
    public ResponseEntity<String>deleteRedirectStaff(@PathVariable("dni")int dni){
        int numDni=service.getOneByDni(dni).getDni();
        return ResponseEntity.status(HttpStatus.OK).body(RedirectorConfirm.url+"?dni="+numDni+"&confirm=");
    }
//AREA
    @PutMapping("actualize-salary/{id_area}/")
    public ResponseEntity<MessageHandler>actualizeSalary(@PathVariable("id_area")short id_area,@Valid @RequestBody AreaDto dto){
        return ResponseEntity.ok().body(service.updateSalaryArea(id_area, dto));
    }
    @GetMapping("allAreas/")
    public ResponseEntity<List<Area>>getAllAreas(){
        return ResponseEntity.ok().body(service.getAllAreas());
    }
    @GetMapping("areaById/{id_area}/")
    public ResponseEntity<Area>getAreaById(@PathVariable("id_area")short id_area){
        return ResponseEntity.ok().body(service.getAreaById(id_area));
    }
}
