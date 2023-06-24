package com.management.staff.security.services.userService;
import com.management.staff.global.exceptions.BusinesException;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.security.dto.user.*;
public interface CreateUserInterface{
    MessageHandler createAdmin(NewUsuarioDto dto)throws BusinesException;
    MessageHandler createTrainee(NewUsuarioDto dto)throws BusinesException;
    MessageHandler createUserEjecutivo(NewUsuarioDto dto)throws BusinesException;
}