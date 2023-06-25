package com.management.staff.controllers;
import com.google.maps.errors.ApiException;
import com.management.staff.dto.areaDto.AreaDto;
import com.management.staff.dto.staffDto.*;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.*;
import com.management.staff.global.utils.validators.DateValidator;
import com.management.staff.models.Address;
import com.management.staff.services.areaService.AreaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Operation(
            summary = "Create staff",
            description = "Crea un staff, se le pasa como primer pathvariable el id del area, como segundo el del puesto. "
                    + "En el body del dto se le pasa el objeto address para generar las coordenadas"
                    + "ADMIN"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("{id_area}/{id_position}/")
    public ResponseEntity<MessageHandler>saveStaff(
        @PathVariable("id_area")short id_area,
        @PathVariable("id_position")short id_position,
        @Valid @RequestBody StaffDto dto)
            throws ResourceNotFoundException,BusinesException,MessagingException,
            ApiException, InterruptedException, IOException{
        
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(areaServiceImpl.saveNewStaff(id_area, id_position, dto));
    }
    @Operation(
            summary = "Update staff address",
            description = "Actualiza la direccion de un staff pasando como pathvariable el dni y en el body el modelo address, "
                    + "para que se guarde las coordenadas en la base de datos. ADMIN"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMINTRAINEE')")
    @PutMapping("update/{dni}/")
    public ResponseEntity<MessageHandler>updateAddressStaff(@PathVariable("dni")int dni,
            @Valid @RequestBody Address address) throws ResourceNotFoundException,ApiException,
            InterruptedException, IOException{
        return ResponseEntity.ok().body(areaServiceImpl.updateAddressOfStaff(dni, address));
    }
    @Operation(
            summary = "Update staff position",
            description = "Actualiza el puesto de un staff pasando como pathvariable el dni y como segundo"
                    + " el id del puesto donde esta actualmente. ADMINTRAINEE"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMINTRAINEE')")
    @PutMapping("actualizar/{dni}/removerde/{id_position}/")
    public ResponseEntity<MessageHandler>updatePositionStaff(
            @PathVariable("dni")int dni,
            @PathVariable("id_position")short id_position,
            @Valid @RequestBody StaffDtoAcenso dto){
        return ResponseEntity.ok()
                .body(areaServiceImpl.updatePositionOfStaff(dni, id_position, dto));
    }
    @Operation(
            summary = "Delete staff",
            description = "Redirecciona a una url para confirmar eliminacion de un staff"
                    + " pasando como pathvariable el dni. ADMIN"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("delete-staff/{dni}/")
    public ResponseEntity<String>deleteRedirectStaff(@PathVariable("dni")int dni){
        int numDni=areaServiceImpl.getOneByDni(dni).getDni();
        return ResponseEntity.status(HttpStatus.OK)
                .body(RedirectorConfirm.url+"?dni="+numDni+"&confirm=");
    }
//AREA
    @Operation(
            summary = "Get all areas",
            description = "Obtiene todas las areas con su cantidad de staffs, total sueldo basico y neto. "
                    + "ADMIN, ADMINTRAINEE, USUARIO, EJECUTIVO"
    )
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USUARIO','ROLE_EJECUTIVO')")
    @GetMapping("allAreas/")
    public ResponseEntity<List<AreaDto>>getAllAreas(){
        return ResponseEntity.ok().body(areaServiceImpl.getAllAreas());
    }
    @Operation(
            summary = "Get area by id",
            description = "Obtiene una area por id con su cantidad de staffs, total sueldo basico y neto. "
                    + "ADMIN, ADMINTRAINEE, USUARIO, EJECUTIVO"
    )
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMINTRAINEE', 'ROLE_USUARIO','ROLE_EJECUTIVO')")
    @GetMapping("areaById/{id_area}/")
    public ResponseEntity<AreaDto>getAreaById(@PathVariable("id_area")short id_area){
        return ResponseEntity.ok().body(areaServiceImpl.getAreaById(id_area));
    }
}