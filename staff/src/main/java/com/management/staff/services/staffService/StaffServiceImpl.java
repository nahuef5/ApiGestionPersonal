package com.management.staff.services.staffService;
import com.management.staff.dto.staffDto.ViewStaffDto;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import com.management.staff.repository.StaffRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffServiceImpl implements StaffServiceInterface{
    @Autowired
    private StaffRepository repository;
    private Set<ViewStaffDto> listDto= new HashSet<>();
    private ViewStaffDto viewDto=new ViewStaffDto();
    //Mensajes de error
    private final String NOTFOUD="No existe ningun trabajador con ese DNI.";
    private final String EMPTY_COLLECTION="La lista de personal esta vacia.";
    
    //metodo para settear el objeto de vista de staff
    private void setViewDto(int dni) throws ResourceNotFoundException{
        Staff staff = repository.findByDni(dni).orElseThrow(()-> new ResourceNotFoundException(this.NOTFOUD));
        this.viewDto.setName(staff.getName());
        this.viewDto.setSurname(staff.getSurname());
        this.viewDto.setBorn(staff.getBorn());
        this.viewDto.setArea(staff.getArea());
        this.viewDto.setPosition(staff.getPosition());
    }
    //metodos para settear coleccion del staff de vista
    private void setlistDto(){
        List<Staff>list=repository.findAll();
        for(Staff stf: list){
            ViewStaffDto dto=new ViewStaffDto(stf.getName(),stf.getSurname(), stf.getBorn(),stf.getArea(), stf.getPosition());
            listDto.add(dto);            
        }       
    }
    @Override
    public Set<ViewStaffDto> getAllViewDto() {
        setlistDto();
        return listDto;
    }
    //metodos heredados para sobreescribir
    @Override
    public List<Staff> getAllWithAllAttributtes() throws ListEmptyException{
        if(repository.findAll().isEmpty())
            throw new ListEmptyException(this.EMPTY_COLLECTION);
        return repository.findAll();
    }

    @Override
    public Staff getOneWithAllAttributes(int dni)throws ResourceNotFoundException {
        Staff staff = repository.findByDni(dni).orElseThrow(()-> new ResourceNotFoundException(this.NOTFOUD));
        return staff;
    }

    @Override
    public ViewStaffDto getOneWithoutSomeAttributes(int dni)throws ResourceNotFoundException {
        setViewDto(dni);
        return viewDto;
    }
}