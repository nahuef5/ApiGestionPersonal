package com.management.staff.services.positionService;
import com.management.staff.dto.positionDto.PositionDto;
import com.management.staff.entities.*;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.models.QueryPageable;
import com.management.staff.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class PositionServiceImpl implements PositionServiceInterface{
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private StaffRepository staffRepository;
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
        float auxBasicSalary=dto.getBasicSalary();
        Position position= positionRepository.findById(id_position).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        position.setBasicSalary(auxBasicSalary);

        //asignamos cada valor a cada staff de la lista
        for(Staff stf: position.getStaff()){
            stf.setBasicSalary(auxBasicSalary);
        }
        MessageHandler msg= new MessageHandler(MessageHandler.UPDATED, HttpStatus.OK);
        return msg;
    }
    @Override
    public Page<Position> getAllByPage(QueryPageable queryPageable) throws ListEmptyException {
        Sort sort= Sort.by(Sort.Direction.fromString(queryPageable.getOrder()),
                queryPageable.getOrderBy());
        Pageable pageable = PageRequest.of(queryPageable.getPage(), queryPageable.getElementByPage(), sort);
        return positionRepository.findAll(pageable);
    }
}