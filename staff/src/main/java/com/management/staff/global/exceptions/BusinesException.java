package com.management.staff.global.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.CONFLICT)
public class BusinesException extends RuntimeException
{public BusinesException(String message){super(message);}}