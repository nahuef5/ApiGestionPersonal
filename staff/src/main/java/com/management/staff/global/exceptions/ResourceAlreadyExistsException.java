package com.management.staff.global.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends Exception
{public ResourceAlreadyExistsException(String message){super(message);}}