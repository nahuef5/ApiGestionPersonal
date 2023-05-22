package com.management.staff.global.exceptions;
import com.management.staff.global.utils.*;
import java.time.format.DateTimeParseException;
import java.util.*;
import org.springframework.http.*;
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
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageHandler> validationException(MethodArgumentNotValidException e){
        List<String> messages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            messages.add(err.getDefaultMessage());
        });
        return ResponseEntity.badRequest()
                .body(new MessageHandler(Trimmer.trimBrackets(messages.toString()),HttpStatus.BAD_REQUEST));
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<MessageHandler>throwListEmpty(ResourceAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new MessageHandler(e.getMessage(), HttpStatus.CONFLICT));
    }
    //DateTimeParseException
    
}