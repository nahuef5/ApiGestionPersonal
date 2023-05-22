package com.management.staff.services.areaService;
import com.management.staff.dto.staffDto.StaffDto;
import com.management.staff.entities.*;
import com.management.staff.enums.AreaEnum;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.repository.*;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AreaServiceImpl implements AreaServiceInterface{
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private StaffRepository staffRepository;
    //Mensajes de error
    
    
    @Override
    public List<Area> getAllAreas() throws ListEmptyException {
        if(areaRepository.findAll().isEmpty()){
            throw new ListEmptyException(MessageHandler.EMPTY_COLLECTION);
        }
        return areaRepository.findAll();
    }

    @Override
    public Area getAreaById(short id) throws ResourceNotFoundException {
        Area area=areaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return area;
    }

    @Override
    public Area getAreaByArea(AreaEnum areaEnum) throws ResourceNotFoundException{
        Area area=areaRepository.findByArea(areaEnum).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return area;
    }

    @Override
    public MessageHandler saveStaff(short id_area, StaffDto dto) throws ResourceNotFoundException, BusinesException{
        if(staffRepository.existsByDni(dto.getDni())){
            throw new BusinesException(MessageHandler.ALREADY_EXISTS);
        }
        Area area = areaRepository.findById(id_area)
                .orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        Staff staff=new Staff(
                dto.getName(),
                dto.getSurname(),
                dto.getAddress(),
                dto.getDni(),
                dto.getBorn(),
                area,
                dto.getPosition());
        staffRepository.save(staff);
        return new MessageHandler(MessageHandler.CREATED, HttpStatus.CREATED);
    }

    @Override
    public MessageHandler updateStaff(String id_staff, StaffDto dto) throws ResourceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MessageHandler patchStaff(String id_staff, Map<String, String> field) throws ResourceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MessageHandler deleteStaff(String id_staff) throws ResourceNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
