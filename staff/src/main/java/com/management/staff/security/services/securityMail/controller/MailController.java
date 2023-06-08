package com.management.staff.security.services.securityMail.controller;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.security.dto.changePassword.ChangePasswordDto;
import com.management.staff.security.services.securityMail.dto.EmailSenderDto;
import com.management.staff.security.services.securityMail.mailService.MailService;
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
    @PostMapping("recover-account")
    public ResponseEntity<MessageHandler>recoverAccount(@RequestBody EmailSenderDto dto) throws MessagingException{
        return ResponseEntity.ok().body(mailService.sendRecover(dto));
    }
    @PostMapping("reset-password")
    public ResponseEntity<MessageHandler>resetPassword(@Valid @RequestBody ChangePasswordDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(mailService.changePassword(dto));
    }
}
