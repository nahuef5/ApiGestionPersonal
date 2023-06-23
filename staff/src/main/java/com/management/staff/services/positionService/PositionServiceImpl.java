package com.management.staff.services.positionService;
import com.management.staff.dto.positionDto.PositionDto;
import com.management.staff.entities.*;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.repository.*;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class PositionServiceImpl implements PositionServiceInterface{
    @Autowired
    private PositionRepository positionRepository;
    
    private Position returnPosition(short id_position){
        Position position=positionRepository.findById(id_position)
                .orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return position;
    }
    private PositionDto convertToPositionDto(Position position){
        PositionDto dto=new PositionDto();
        dto.setName(position.getPosition().name());
        dto.setBasicSalary(position.getBasicSalary());
        return dto;
    }
    @Override
    public List<PositionDto> getAllPositions() throws ListEmptyException {
        if(positionRepository.findAll().isEmpty())
            throw new ListEmptyException(MessageHandler.EMPTY_COLLECTION);
        return positionRepository.findAll().stream()
                .map(position -> convertToPositionDto(position))
                .collect(Collectors.toList());
    }

    @Override
    public Position getPositionById(short id_position) throws ResourceNotFoundException {
        Position position = returnPosition(id_position);
        return position;
    }
    @Override
    public MessageHandler updateSalaryByPosition(short id_position, PositionDto dto) throws ResourceNotFoundException {
        double auxBasicSalary=dto.getBasicSalary();
        Position position = returnPosition(id_position);
        position.setBasicSalary(auxBasicSalary);

        //asignamos cada valor a cada staff de la lista
        position.getStaff()
                .stream()
                .forEach(
                stf->stf.setBasicSalary(auxBasicSalary));
        return new MessageHandler(MessageHandler.UPDATED, HttpStatus.OK);
    }
}