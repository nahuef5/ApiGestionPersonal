package com.management.staff.global.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NO_CONTENT)
public class ListEmptyException extends Exception
{public ListEmptyException(String message){super(message);}}