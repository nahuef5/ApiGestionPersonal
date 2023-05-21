package com.management.staff.global.exceptions;
import com.management.staff.global.utils.MessageHandler;
import java.util.*;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalException{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageHandler>throwResourceNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageHandler(e.getMessage(), HttpStatus.NOT_FOUND));
    }
    @ExceptionHandler(ListEmptyException.class)
    public ResponseEntity<MessageHandler>throwListEmpty(ListEmptyException e){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new MessageHandler(e.getMessage(), HttpStatus.NO_CONTENT));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>throwValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((err)->{
            String field = ((FieldError)err).getField();
            String message = err.getDefaultMessage();
            
            errors.put(field, message);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}