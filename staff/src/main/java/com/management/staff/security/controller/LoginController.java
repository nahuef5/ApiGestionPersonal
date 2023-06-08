package com.management.staff.security.controller;
import com.management.staff.security.dto.token.JwtToken;
import com.management.staff.security.dto.user.LoginUserDto;
import com.management.staff.security.services.userService.UsuarioService;
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
    @PostMapping("")
    public ResponseEntity<JwtToken> login(@Valid @RequestBody LoginUserDto dto){
        return ResponseEntity.ok(usuarioService.loginUser(dto));
    }
    @PostMapping("refresh-token")
    public JwtToken refreshToken(@RequestBody JwtToken token) throws ParseException{
        return usuarioService.getRefreshToken(token);
    }
}