package com.management.staff.controllers;
import com.management.staff.global.utils.*;
import com.management.staff.services.areaService.AreaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/confirm/delete-resource/")
public class RedirectConfirmController {
    @Autowired
    private AreaServiceImpl service;
    @Operation(
            summary = "Confirm Delete Staff",
            description = "Elimina el staff desde el dni confirmando en el request param. "
                    + "ADMIN"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<MessageHandler> confirmDeleteStaff(
            @RequestParam("dni")int dni,
            @RequestParam("confirm")boolean confirm){
        
        if(!confirm)
            return ResponseEntity.ok(new MessageHandler(MessageHandler.NOT_REMOVED, HttpStatus.OK));

        return ResponseEntity.ok(service.deleteStaff(dni));
    }
}