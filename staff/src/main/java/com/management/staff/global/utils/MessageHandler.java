package com.management.staff.global.utils;
import lombok.*;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class MessageHandler {
    private String message;
    private HttpStatus status;
    //Mensajes de error
    public static final String NOT_FOUD="There is no such resource, please try another identifier.";
    public static final String EMPTY_COLLECTION="The list of the resource you want to read is momentarily empty.";
    public static final String ALREADY_EXISTS="A resource with that identifier already exists.";
    //Mensajes de exito
    public static final String CREATED="Staff has been successfully created";
    public static final String UPDATED="Staff has been successfully updated";
    public static final String ELIMINATED="Staff has been successfully eliminated";
    public static final String NOT_REMOVED="The resource hasn´t been removed";
    
}
