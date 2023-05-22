package com.management.staff.controllers;
import com.management.staff.dto.staffDto.StaffDto;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.global.utils.validators.DateValidator;
import com.management.staff.services.areaService.AreaServiceImpl;
import jakarta.validation.Valid;
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
    
//Ejecutar actualizacion de rango de fecha cada 1ro de enero
    @Scheduled(cron = "0 0 0 1 1 *") 
    public void actualizarLimitesFecha() {
        DateValidator.updatedLocalDate = true;
    }
    @PostMapping("{id_area}/")
    public ResponseEntity<MessageHandler>saveStaff
        (@PathVariable("id_area")short id_area, @Valid @RequestBody StaffDto dto)
                throws ResourceNotFoundException, BusinesException{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.saveStaff(id_area, dto));
    }
}
