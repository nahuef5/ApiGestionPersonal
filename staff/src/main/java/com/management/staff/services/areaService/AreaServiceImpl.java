package com.management.staff.services.areaService;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.*;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.*;
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
public class AreaServiceImpl implements AreaServiceInterface{
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private StaffRepository staffRepository;    
    @Autowired
    private PositionRepository positionRepository;
    
    //obtiene lista de areas
    @Override
    public List<Area> getAllAreas() throws ListEmptyException {
        if(areaRepository.findAll().isEmpty()){
            throw new ListEmptyException(MessageHandler.EMPTY_COLLECTION);
        }
        return areaRepository.findAll();
    }
    //obtiene el area por id
    @Override
    public Area getAreaById(short id) throws ResourceNotFoundException {
        Area area=areaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return area;
    }
    //guarda un staff pasamos id de area y de posicion
    @Override
    public MessageHandler saveNewStaff(short id_area,short id_position,StaffDto dto)throws ResourceNotFoundException, BusinesException{
        if(staffRepository.existsByDni(dto.getDni()))
            throw new BusinesException(MessageHandler.ALREADY_EXISTS);
        Area area = areaRepository.findById(id_area).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        Position position= positionRepository.findById(id_position).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        Staff staff= new Staff(
                dto.getName(),
                dto.getSurname(),
                dto.getAddress(),
                dto.getDni(),
                dto.getBorn(),
                area,
                position);
        staffRepository.save(staff);
        MessageHandler msg = new MessageHandler(MessageHandler.CREATED, HttpStatus.CREATED);
        return msg;
    }
    //utilizamos dtoAddress para actualizar domicilio de staff, pasamos dni para saber cual hay que actualizar
    @Override
    public MessageHandler updateAddressOfStaff(int dni, StaffAddressDto dto) throws ResourceNotFoundException{
        Staff staff=staffRepository.findByDni(dni).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        staff.setAddress(dto.getAddress());        
        staffRepository.save(staff);
        MessageHandler msg=new MessageHandler(MessageHandler.UPDATED, HttpStatus.OK);
        return msg;
    }
    //modificamos la posicion de staff, junto con el salario correspondiente al area
    @Override
    public MessageHandler updatePositionOfStaff(int dni,short id_position, StaffDtoAcenso dto) throws ResourceNotFoundException{
        Staff staff=staffRepository.findByDni(dni).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        Position position= positionRepository.findById(id_position).get();
        if(staff.getPosition()!= position){
            throw new BusinesException("Ese puesto no corresponde a ese personal.");
        }
        //debemos remover el staff de la lista anterior del puesto pasando su id
        position.getStaff().remove(staff);
        
        //obtenemos la posicion del id dto
        Position updateByDto=positionRepository.findById(dto.getId_position()).get();
        //a staff le asignamos una nueva posicion
        staff.setPosition(updateByDto);
        //actualizamos el sueldo bsico del staff con el de la posicion que le corresponde
        staff.setGrossSalary(updateByDto.getBasicSalary());
        
        staffRepository.save(staff);
        //tenemos que asignar este staff a la lista nueva posicion
        updateByDto.addStaff(staff);
        
        MessageHandler msg=new MessageHandler(MessageHandler.UPDATED, HttpStatus.OK);
        return msg;
    }
    //eliminar staff y remover de la lista de posicion y area
    @Override
    public MessageHandler deleteStaff(int dni) throws ResourceNotFoundException {
        Staff staff = staffRepository.findByDni(dni).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        //sacamos staf de la lista de position para liberar memoria
        Position position=positionRepository.findByPosition(staff.getPosition().getPosition()).get();
        position.getStaff().remove(staff);
        //eliminamos staff
        staffRepository.delete(staff);
        
        MessageHandler msg=new MessageHandler(MessageHandler.ELIMINATED, HttpStatus.OK);
        return msg;
    }
    //obtiene un staff por dni
    @Override
    public Staff getOneByDni(int dni){
        Staff staff =staffRepository.findByDni(dni).orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return staff;
    }
    @Override
    public Page<Area> getAllByPage(QueryPageable queryPageable) throws ListEmptyException {
        Sort sort= Sort.by(Sort.Direction.fromString(queryPageable.getOrder()),
                queryPageable.getOrderBy());
        Pageable pageable = PageRequest.of(queryPageable.getPage(), queryPageable.getElementByPage(), sort);
        return areaRepository.findAll(pageable);
    }
}