package com.management.staff.services.areaService;
import com.management.staff.dto.areaDto.AreaDto;
import com.management.staff.dto.staffDto.StaffDto;
import com.management.staff.dto.staffDto.StaffDtoPatch;
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
    MessageHandler saveStaff(short id_area,StaffDto dto)throws ResourceNotFoundException, BusinesException;
//actualizar staff solo position y  direccion
    MessageHandler updateStaff(int dni, StaffDtoPatch dto)throws ResourceNotFoundException;
//Solo se actualizaran los sueldos por area
    MessageHandler updateSalaryArea(short id_area, AreaDto dto)throws ResourceNotFoundException, BusinesException;
//Eliminar staff luego de la confirmacion
    MessageHandler deleteStaff(int dni)throws ResourceNotFoundException;
//Metodo con que se obtenda un staff para luego tomarlo y eliminarlo
    public Staff getOneByDni(int dni);
}
