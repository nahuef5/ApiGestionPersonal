package com.management.staff.controllers;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.Area;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.*;
import com.management.staff.global.utils.validators.DateValidator;
import com.management.staff.models.QueryPageable;
import com.management.staff.services.areaService.AreaServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/byArea/")
@CrossOrigin(origins="http://localhost:5000/")
public class AreaController{
    @Autowired
    AreaServiceImpl areaServiceImpl;
    
//Ejecutar actualizacion de rango de fecha cada PRIMERO de ENERO
    @Scheduled(cron = "0 0 0 1 1 *") 
    public void actualizarLimitesFecha() {
        DateValidator.updatedLocalDate = true;
    }
//STAFF
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("{id_area}/{id_position}/")
    public ResponseEntity<MessageHandler>saveStaff//ta
        (@PathVariable("id_area")short id_area,@PathVariable("id_position")short id_position, @Valid @RequestBody StaffDto dto)
                throws ResourceNotFoundException, BusinesException{
            return ResponseEntity.status(HttpStatus.CREATED).body(areaServiceImpl.saveNewStaff(id_area, id_position, dto));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINTRAINEE')")
    @PutMapping("update/{dni}/")//ta
    public ResponseEntity<MessageHandler>updateAddressStaff(@PathVariable("dni")int dni,@Valid @RequestBody StaffAddressDto dto){
        return ResponseEntity.ok().body(areaServiceImpl.updateAddressOfStaff(dni, dto));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINTRAINEE')")
    @PutMapping("actualizar/{dni}/removerde/{id_position}/")
    public ResponseEntity<MessageHandler>updatePositionStaff(@PathVariable("dni")int dni,@PathVariable("id_position")short id_position,@Valid @RequestBody StaffDtoAcenso dto){
        return ResponseEntity.ok().body(areaServiceImpl.updatePositionOfStaff(dni, id_position, dto));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("delete-staff/{dni}/")
    public ResponseEntity<String>deleteRedirectStaff(@PathVariable("dni")int dni){
        int numDni=areaServiceImpl.getOneByDni(dni).getDni();
        return ResponseEntity.status(HttpStatus.OK).body(RedirectorConfirm.url+"?dni="+numDni+"&confirm=");
    }
//AREA
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USUARIO')")
    @GetMapping("allAreas/")
    public ResponseEntity<List<Area>>getAllAreas(){
        return ResponseEntity.ok().body(areaServiceImpl.getAllAreas());
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'USUARIO')")
    @GetMapping("areaById/{id_area}/")
    public ResponseEntity<Area>getAreaById(@PathVariable("id_area")short id_area){
        return ResponseEntity.ok().body(areaServiceImpl.getAreaById(id_area));
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'USUARIO')")
    @GetMapping("")
    public ResponseEntity<Page<Area>> getAll(QueryPageable queryPageable){
        return ResponseEntity.ok(areaServiceImpl.getAllByPage(queryPageable));
    }
}