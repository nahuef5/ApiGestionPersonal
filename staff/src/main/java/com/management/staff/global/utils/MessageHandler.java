package com.management.staff.global.utils;
import lombok.*;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class MessageHandler {
    private String message;
    private HttpStatus status;
}
