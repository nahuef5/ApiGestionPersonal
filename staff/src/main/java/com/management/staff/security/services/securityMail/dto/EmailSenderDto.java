package com.management.staff.security.services.securityMail.dto;
import lombok.*;
@NoArgsConstructor
@Getter @Setter
public class EmailSenderDto {
    //recover password
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String username;
    private String forgottenPassword;
    //send staff
    private String name;
    private String password;
    public EmailSenderDto(String mailFrom, String mailTo, String subject, String username, String forgottenPassword) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.username = username;
        this.forgottenPassword = forgottenPassword;
    }
    public EmailSenderDto(String mailFrom, String mailTo, String subject, String username, String name, String password) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.username = username;
        this.name = name;
        this.password = password;
    }
}