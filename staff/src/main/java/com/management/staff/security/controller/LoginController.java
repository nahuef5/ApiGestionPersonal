package com.management.staff.security.controller;
import com.management.staff.security.dto.token.JwtToken;
import com.management.staff.security.dto.user.LoginUserDto;
import com.management.staff.security.services.userService.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/")
@CrossOrigin(origins="http://localhost:5000/")
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;
    @Operation(
            summary = "Login",
            description = "Iniciar sesion"
    )
    @PostMapping("")
    public ResponseEntity<JwtToken> login(@Valid @RequestBody LoginUserDto dto){
        return ResponseEntity.ok(usuarioService.loginUser(dto));
    }
    @Operation(
            summary = "Refresh token",
            description = "Actualizacion del token luego de 8 dias"
    )
    @PostMapping("refresh-token")
    public JwtToken refreshToken(@RequestBody JwtToken token) throws ParseException{
        return usuarioService.getRefreshToken(token);
    }
}