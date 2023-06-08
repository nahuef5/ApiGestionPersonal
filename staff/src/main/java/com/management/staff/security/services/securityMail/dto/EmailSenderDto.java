package com.management.staff.security.services.securityMail.dto;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class EmailSenderDto {
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String username;
    private String forgottenPassword;
}