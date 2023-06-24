package com.management.staff.security.controller;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.security.dto.user.*;
import com.management.staff.security.services.userService.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("")
@CrossOrigin(origins="http://localhost:5000/")
public class CreateUserController{
    @Autowired
    private UsuarioService usuarioService;
    @Operation(
            summary = "Create executive user",
            description = "Crear usuario ejecutivo."
    )
    @PostMapping("/reg-executive")
    public ResponseEntity<MessageHandler>createUserEjecutivo(@Valid @RequestBody NewUsuarioDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUserEjecutivo(dto));
    }
    @Operation(
            summary = "Create admin-trainee user",
            description = "Crear usuario administrador trainee. Desde ADMIN"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/reg-trainee")
    public ResponseEntity<MessageHandler>createAdminTrainee(@Valid @RequestBody NewUsuarioDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createTrainee(dto));
    }
    @Operation(
            summary = "Create admin user",
            description = "Crear usuario administrador."
    )
    @PostMapping("/reg-admin")
    public ResponseEntity<MessageHandler>createUserAdmin(@Valid @RequestBody NewUsuarioDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createAdmin(dto));
    }
    
}