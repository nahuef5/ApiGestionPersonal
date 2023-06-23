package com.management.staff.services.positionService;
import com.management.staff.dto.positionDto.PositionDto;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import java.util.List;
public interface PositionServiceInterface {
    List<PositionDto> getAllPositions()throws ListEmptyException;
    //Actualizar sueldo por posicion
    MessageHandler updateSalaryByPosition(short id_position, PositionDto dto)throws ResourceNotFoundException;
}