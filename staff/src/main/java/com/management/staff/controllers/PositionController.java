package com.management.staff.controllers;
import com.management.staff.dto.positionDto.PositionDto;
import com.management.staff.entities.Position;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.services.positionService.PositionServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/position/")
public class PositionController {
    @Autowired
    private PositionServiceImpl service;
    @GetMapping("allPositions/")
    public ResponseEntity<List<Position>> getAllPosition(){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllPositions());
    }
    @GetMapping("positionById/{id_position}/")
    public ResponseEntity<Position>getOneById(@PathVariable ("id_position")short id_position){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getPositionById(id_position));
    }
    @PutMapping("updateSalary/{id_position}/")
    public ResponseEntity<MessageHandler>updateSalaryByPosition(@PathVariable("id_position")short id_position,@Valid @RequestBody PositionDto dto){
        return ResponseEntity.ok(service.updateSalaryByPosition(id_position, dto));
    }
}