package com.management.staff.services.areaService;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.Area;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.*;
import java.util.*;
public interface AreaServiceInterface{
//Get por area
    List<Area>getAllAreas()throws ListEmptyException;
    Area getAreaById(short id)throws ResourceNotFoundException;
//Crear staff desde el area
    MessageHandler saveNewStaff(short id_area,short id_position,StaffDto dto)throws ResourceNotFoundException, BusinesException;
//actualizar staff solo position y  direccion
    MessageHandler updateAddressOfStaff(int dni, StaffAddressDto dto)throws ResourceNotFoundException;
    
    MessageHandler updatePositionOfStaff(int dni,short id_position, StaffDtoAcenso dto) throws ResourceNotFoundException;
//Eliminar staff luego de la confirmacion
    MessageHandler deleteStaff(int dni)throws ResourceNotFoundException;
//Metodo con que se obtenda un staff para luego tomarlo y eliminarlo
    public Staff getOneByDni(int dni);
}
