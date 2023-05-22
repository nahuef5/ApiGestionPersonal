package com.management.staff.services.areaService;
import com.management.staff.dto.staffDto.StaffDto;
import com.management.staff.entities.Area;
import com.management.staff.enums.AreaEnum;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import java.util.*;
public interface AreaServiceInterface{
    //Get por area
    List<Area>getAllAreas()throws ListEmptyException;
    Area getAreaById(short id)throws ResourceNotFoundException;
    Area getAreaByArea(AreaEnum areaEnum)throws ResourceNotFoundException;
    //Post staff 
    MessageHandler saveStaff(short id_area,StaffDto dto)throws ResourceNotFoundException, BusinesException;
    //Update staff con todos los atributos
    MessageHandler updateStaff(String id_staff, StaffDto dto)throws ResourceNotFoundException;
    //Update staff con patch
    MessageHandler patchStaff(String id_staff, Map<String, String>field)throws ResourceNotFoundException;
    //Delete staff
    MessageHandler deleteStaff(String id_staff)throws ResourceNotFoundException;
}
