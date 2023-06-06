package com.management.staff.security.services;
import com.management.staff.global.exceptions.BusinesException;
import com.management.staff.security.dto.NewUsuarioDto;
import com.management.staff.security.entities.Usuario;
public interface CreateUserInterface{
    Usuario createAdmin(NewUsuarioDto dto)throws BusinesException;
    Usuario createTrainee(NewUsuarioDto dto)throws BusinesException;
    Usuario createUser(NewUsuarioDto dto)throws BusinesException;
}