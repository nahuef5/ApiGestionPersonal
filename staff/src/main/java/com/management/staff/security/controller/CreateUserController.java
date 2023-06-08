package com.management.staff.security.controller;
import com.management.staff.security.dto.user.*;
import com.management.staff.security.entities.Usuario;
import com.management.staff.security.services.userService.UsuarioService;
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
    @PostMapping("/register")
    public ResponseEntity<Usuario>createNormalUser(@Valid @RequestBody NewUsuarioDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUser(dto));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/registerTrainee")
    public ResponseEntity<Usuario>createAdminTrainee(@Valid @RequestBody NewUsuarioDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createTrainee(dto));
    }
    @PostMapping("/reg-admin")
    public ResponseEntity<Usuario>createUserAdmin(@Valid @RequestBody NewUsuarioDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createAdmin(dto));
    }
    
}