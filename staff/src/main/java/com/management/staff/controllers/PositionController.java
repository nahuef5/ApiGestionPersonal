package com.management.staff.controllers;
import com.management.staff.dto.positionDto.PositionDto;
import com.management.staff.entities.Position;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.models.QueryPageable;
import com.management.staff.services.positionService.PositionServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/position/")
@CrossOrigin(origins="http://localhost:5000/")
public class PositionController {
    @Autowired
    private PositionServiceImpl service;
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USER')")
    @GetMapping("allPositions/")
    public ResponseEntity<List<Position>> getAllPosition(){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllPositions());
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USER')")
    @GetMapping("positionById/{id_position}/")
    public ResponseEntity<Position>getOneById(@PathVariable ("id_position")short id_position){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getPositionById(id_position));
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USER')")
    @GetMapping("")
    public ResponseEntity<Page<Position>> getAll(QueryPageable queryPageable){
        return ResponseEntity.ok(service.getAllByPage(queryPageable));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("updateSalary/{id_position}/")
    public ResponseEntity<MessageHandler>updateSalaryByPosition(
            @PathVariable("id_position")short id_position,
            @Valid @RequestBody PositionDto dto){
        return ResponseEntity.ok(service.updateSalaryByPosition(id_position, dto));
    }
}