package com.management.staff.services.positionService;
import com.management.staff.dto.positionDto.PositionDto;
import com.management.staff.entities.Position;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.models.QueryPageable;
import java.util.List;
import org.springframework.data.domain.Page;
public interface PositionServiceInterface {
    List<Position> getAllPositions()throws ListEmptyException;
    Position getPositionById(short id_position)throws ResourceNotFoundException;
    //Pagination
    Page<Position>getAllByPage(QueryPageable queryPageable)throws ListEmptyException;
    //Actualizar sueldo por posicion
    MessageHandler updateSalaryByPosition(short id_position, PositionDto dto)throws ResourceNotFoundException;
}