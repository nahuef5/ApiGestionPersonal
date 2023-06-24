package com.management.staff.security.services.securityMail.controller;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.security.dto.changePassword.ChangePasswordDto;
import com.management.staff.security.services.securityMail.dto.EmailSenderDto;
import com.management.staff.security.services.securityMail.mailService.MailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/email/")
@CrossOrigin(origins="http://localhost:5000")
public class MailController {
    @Autowired
    private MailService mailService;
    @Operation(
            summary = "Recover account with username",
            description = "Implementacion de envio de email para un olvido de contraseña. Se pasa el username en el body."
    )
    @PostMapping("recover-account")
    public ResponseEntity<MessageHandler>recoverAccount(@RequestBody EmailSenderDto dto) throws MessagingException{
        return ResponseEntity.ok().body(mailService.sendRecover(dto));
    }
    @Operation(
            summary = "Reset password",
            description = "Pasa como parametro contraseña y la confirmacion, mas el codigo que se utiliza para el olvido."
    )
    @PostMapping("reset-password")
    public ResponseEntity<MessageHandler>resetPassword(@Valid @RequestBody ChangePasswordDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(mailService.changePassword(dto));
    }
}
