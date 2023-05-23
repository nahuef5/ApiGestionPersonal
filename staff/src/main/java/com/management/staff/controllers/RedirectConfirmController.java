package com.management.staff.controllers;
import com.management.staff.global.utils.*;
import com.management.staff.services.areaService.AreaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/confirm/delete-resource/")
public class RedirectConfirmController {
    @Autowired
    private AreaServiceImpl service;
    
    @GetMapping()
    public ResponseEntity<MessageHandler> confirmDeleteStaff(
            @RequestParam("dni")int dni,
            @RequestParam("confirm")boolean confirm){
        
        if(!confirm)
            return ResponseEntity.ok(new MessageHandler(MessageHandler.NOT_REMOVED, HttpStatus.OK));

        return ResponseEntity.ok(service.deleteStaff(dni));
    }
}