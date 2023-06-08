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
    public static final String NOT_FOUD="No existe ese recurso.";
    public static final String EMPTY_COLLECTION="La lista de recursos se encuentra vacía.";
    public static final String ALREADY_EXISTS="Ya existe un recurso con ese dni, ese mail o ese nombre de usuario.";
    //Mensajes de exito
    public static final String CREATED="Recurso creado exitosamente.";
    public static final String UPDATED="Recurso actulizado correctamente.";
    public static final String ELIMINATED="Recurso eliminado correctamente.";
    public static final String NOT_REMOVED="El recurso no ha sido eliminado.";
    //Mensajes del email
    public static final String RESET_PASSWORD="Revisa la casilla de mensajes de tu mail para restaurar la contraseña.";
    public static final String PASSWORD_CHANGED="Contraseña actualizada exitosamente.";
}