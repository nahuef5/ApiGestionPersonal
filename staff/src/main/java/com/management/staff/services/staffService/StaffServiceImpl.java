package com.management.staff.services.staffService;
import com.management.staff.dto.staffDto.StaffDto;
import com.management.staff.dto.staffDto.AnyoneReadsStaffDto;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.MessageHandler;
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
    
        //COLECCION DE VISTA UNIVERSAL
    private Set<AnyoneReadsStaffDto> listaUniversal= new HashSet<>();
        //OBJETO VISTA UNIVERSAL
    private AnyoneReadsStaffDto individual_universal=new AnyoneReadsStaffDto();
        //OBJETO VISTA AUTENTICADA
    private StaffDto individual_autenticado=new StaffDto();
    
    /*Objetos*/
    //SETEADOR STAFF VISTA AUTENTICADA
    private void setIndividualAutenticado(int dni) throws ResourceNotFoundException{
        Staff staff = repository.findByDni(dni).orElseThrow(()-> new ResourceNotFoundException(MessageHandler.NOT_FOUD));
                individual_autenticado.setName(
                        staff.getName());
                individual_autenticado.setSurname(
                        staff.getSurname());
                individual_autenticado.setAddress(
                        staff.getAddress());
                individual_autenticado.setDni(
                        staff.getDni());
                individual_autenticado.setBorn(
                        staff.getBorn());
                individual_autenticado.setArea(
                        staff.getArea().getArea().name());
                individual_autenticado.setPosition(
                        staff.getPosition().getPosition().name());
                individual_autenticado.setGrossSalary(
                        staff.getGrossSalary());
                individual_autenticado.setNetSalary(
                        staff.getNetSalary());
    }
        
        //GET AUTENTICADO
    @Override
    public StaffDto getIndividualAutenticado(int dni)throws ResourceNotFoundException {
        setIndividualAutenticado(dni);
        return individual_autenticado;
    }
        //SETEADOR STAFF VISTA UNIVERSAL
    private void setIndividualUniversal(int dni) throws ResourceNotFoundException{
        Staff staff = repository.findByDni(dni).orElseThrow(()-> 
                new ResourceNotFoundException(MessageHandler.NOT_FOUD));
            individual_universal.setName(
                    staff.getName());
            individual_universal.setSurname(
                    staff.getSurname());
            individual_universal.setBorn(
                    staff.getBorn());
            individual_universal.setDni(
                    staff.getDni());
                //TRAER VALOR NOMBRE DEL ENUM
            individual_universal.setArea(
                    staff.getArea().getArea().name());
            individual_universal.setPosition(
                    staff.getPosition().getPosition().name());
    }
        //GET UNIVERSAL
    @Override
    public AnyoneReadsStaffDto getIndividualUniversal(int dni)throws ResourceNotFoundException {
        setIndividualUniversal(dni);
        return individual_universal;
    }
    
    /*Listas*/
    
        //SETTEAR COLECCION STAFF DE VISTA UNIVERSAL
    private void setListaUniversal()throws ListEmptyException{
        if(repository.findAll().isEmpty())
            throw new ListEmptyException(MessageHandler.EMPTY_COLLECTION);
        for(Staff stf: repository.findAll()){
            AnyoneReadsStaffDto dto=new AnyoneReadsStaffDto(
                    stf.getName(),
                    stf.getSurname(),
                    stf.getBorn(),
                    stf.getDni(),
                    stf.getArea().getArea().name(), 
                    stf.getPosition().getPosition().name());
            listaUniversal.add(dto);            
        }       
    }
        //GET LISTA UNIVERSAL
    @Override
    public Set<AnyoneReadsStaffDto> getListaUniversal() throws ListEmptyException{
        setListaUniversal();
        return listaUniversal;
    }
        //GET AUTENTICADO
    @Override
    public List<Staff> getListaAutenticado() throws ListEmptyException{
        if(repository.findAll().isEmpty()){
            throw new ListEmptyException(MessageHandler.EMPTY_COLLECTION);
        }
        return repository.findAll();
    }
}