package com.management.staff.services.positionService;
import com.management.staff.dto.positionDto.PositionDto;
import com.management.staff.entities.Position;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import java.util.List;
public interface PositionServiceInterface {
    List<Position> getAllPositions()throws ListEmptyException;
    Position getPositionById(short id_position)throws ResourceNotFoundException;
    //Actualizar sueldo por posicion
    MessageHandler updateSalaryByPosition(short id_position, PositionDto dto)throws ResourceNotFoundException;
}