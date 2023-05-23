package com.management.staff.services.areaService;
import com.management.staff.dto.areaDto.AreaDto;
import com.management.staff.dto.staffDto.StaffDto;
import com.management.staff.dto.staffDto.StaffDtoPatch;
import com.management.staff.entities.*;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.*;
import com.management.staff.repository.*;
import java.util.List;
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
    //Funciona: guarda Staff
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
        MessageHandler msg=new MessageHandler(MessageHandler.CREATED, HttpStatus.CREATED);
        return msg;
    }
    //Funciona: actualiza staff
    @Override
    public MessageHandler updateStaff(int dni, StaffDtoPatch dto) throws ResourceNotFoundException{
        Staff staff=staffRepository.findByDni(dni).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        
        staff.setAddress(dto.getAddress());
        staff.setPosition(dto.getPosition());
        
        staffRepository.save(staff);
        MessageHandler msg=new MessageHandler(MessageHandler.UPDATED, HttpStatus.OK);
        return msg;
    }
    //Funciona: elimina staff
    @Override
    public MessageHandler deleteStaff(int dni) throws ResourceNotFoundException {
        Staff staff = staffRepository.findByDni(dni).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        staffRepository.delete(staff);
        MessageHandler msg=new MessageHandler(MessageHandler.ELIMINATED, HttpStatus.OK);
        return msg;
    }
    //Funciona: trae un staff
    @Override
    public Staff getOneByDni(int dni){
        Staff staff =staffRepository.findByDni(dni).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return staff;
    }
    //
    @Override
    public MessageHandler updateSalaryArea(short id_area, AreaDto dto) throws ResourceNotFoundException, BusinesException {
        int auxGrossSalary=dto.getGrossSalary();
        int auxNetSalary=dto.getNetSalary();
        Area area=areaRepository.findById(id_area).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        area.setGrossSalary(id_area,auxGrossSalary );
        area.setNetSalary(id_area, auxNetSalary);
        
        //Le asignamos cada valor a cada staff, de lo contrario no se actualizaria
        for(Staff stf : areaRepository.findById(id_area).get().getStaff()){
            stf.setGrossSalary(auxGrossSalary);
            stf.setNetSalary(auxNetSalary);
        }
        MessageHandler msg=new MessageHandler(MessageHandler.UPDATED, HttpStatus.OK);
        return msg;
    }
    
}
