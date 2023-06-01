package com.management.staff.services.positionService;
import com.management.staff.dto.positionDto.PositionDto;
import com.management.staff.entities.*;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.repository.PositionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class PositionServiceImpl implements PositionServiceInterface{
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getAllPositions() throws ListEmptyException {
        if(positionRepository.findAll().isEmpty())
            throw new ListEmptyException(MessageHandler.EMPTY_COLLECTION);
        return positionRepository.findAll();
    }

    @Override
    public Position getPositionById(short id_position) throws ResourceNotFoundException {
        Position position = positionRepository.findById(id_position).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return position;
    }
    @Override
    public MessageHandler updateSalaryByPosition(short id_position, PositionDto dto) throws ResourceNotFoundException {
        float auxGrossSalary=dto.getGrossSalary();
        float auxNetSalary=dto.getNetSalary();
        Position position= positionRepository.findById(id_position).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        position.setGrossSalary(auxGrossSalary);
        position.setNetSalary(auxNetSalary);
        //asignamos cada valor a cada staff de la lista
        for(Staff stf: position.getStaff()){
            stf.setGrossSalary(auxGrossSalary);
            stf.setNetSalary(auxNetSalary);
        }
        MessageHandler msg= new MessageHandler(MessageHandler.UPDATED, HttpStatus.OK);
        return msg;
    }
}