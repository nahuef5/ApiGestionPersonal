package com.management.staff.services.areaService;
import com.management.staff.dto.areaDto.AreaDto;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.*;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.*;
import jakarta.mail.MessagingException;
import java.util.*;
public interface AreaServiceInterface{
//Get por area
    List<AreaDto>getAllAreas()throws ListEmptyException;
    AreaDto getAreaById(short id)throws ResourceNotFoundException;
//Crear staff desde el area
    MessageHandler saveNewStaff(short id_area,short id_position,StaffDto dto)throws ResourceNotFoundException, BusinesException, MessagingException;
//actualizar staff solo position y  direccion
    MessageHandler updateAddressOfStaff(int dni, StaffAddressDto dto)throws ResourceNotFoundException;
    
    MessageHandler updatePositionOfStaff(int dni,short id_position, StaffDtoAcenso dto) throws ResourceNotFoundException;
//Eliminar staff luego de la confirmacion
    MessageHandler deleteStaff(int dni)throws ResourceNotFoundException;
//Metodo con que se obtenda un staff para luego tomarlo y eliminarlo
    public Staff getOneByDni(int dni);
}
