package com.management.staff.services.areaService;
import com.google.maps.errors.ApiException;
import com.management.staff.dto.areaDto.AreaDto;
import com.management.staff.dto.staffDto.*;
import com.management.staff.entities.*;
import com.management.staff.enums.AreaEnum;
import com.management.staff.global.exceptions.*;
import com.management.staff.global.utils.*;
import com.management.staff.models.Address;
import com.management.staff.repository.*;
import com.management.staff.security.entities.*;
import com.management.staff.security.enums.RoleEnum;
import com.management.staff.security.repository.*;
import com.management.staff.security.services.roleService.RoleService;
import com.management.staff.security.services.securityMail.mailService.MailService;
import com.management.staff.services.externalServices.GoogleMapsServices;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private GoogleMapsServices googleMapsServices;
    
    private Area returnArea(short id_area){
        Area area= areaRepository.findById(id_area).orElseThrow(
                ()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return area;
    }
    private Position returnPosition(short id_position){
        Position position=positionRepository.findById(id_position)
                .orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return position;
    }
    private Staff returnStaff(int dni){
        Staff staff = staffRepository.findByDni(dni)
                .orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return staff;
    }
    private int getTotalStaff(Set<Staff>staff){
        return staff.size();
    }
    private double getTotalBasic(Set<Staff>staff){
        return staff.stream()
                .mapToDouble(stf -> stf.getBasicSalary())
                .sum();
    }
    private double getTotalGross(Set<Staff>staff){
        return staff.stream()
                .mapToDouble(stf -> stf.getGrossSalary())
                .sum();
    }
    private AreaDto convertToAreaDto(Area area){
        AreaDto dto = new AreaDto();
        dto.setId_area(area.getId_area());
        dto.setArea(area.getArea().name());
        dto.setAllStaff(getTotalStaff(area.getStaff()));
        dto.setTotalBasicSalarys(getTotalBasic(area.getStaff()));
        dto.setTotalGrossSalary(getTotalGross(area.getStaff()));
        return dto;
    }
    //Guardar un usuario con un staff
    private Usuario saveUsuarioByStaff(Staff staff){
        //sustraemos parte del id del staff para luego concatenar a username del user
        String subStringID = staff.getId_staff().substring(2, 10);        
        //password sera name, area y dni del staff
        String passwordEncode=passwordEncoder.encode
                (
                    staff.getName()+"_"+
                    staff.getAreaName()+"_"+
                    subStringID
                );
        
        //sustraemos parte del dni del staff para luego concatenar a username del user
        String subStringDNI = String.valueOf(staff.getDni()).substring(5);
        
        String username=
                staff.getName()
                +
                staff.getSurname()
                +
                subStringDNI;
        
        Usuario user=new Usuario(
                                staff.getDni(),
                                staff.getEmail(),
                                username,
                                passwordEncode);
        
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleByName(RoleEnum.ROLE_USUARIO).get());
        user.setRoles(roles);
        return usuarioRepository.save(user);
    }
    //obtiene lista de areas
    @Override
    public List<AreaDto> getAllAreas() throws ListEmptyException {
        if(areaRepository.findAll().isEmpty())
            throw new ListEmptyException(MessageHandler.EMPTY_COLLECTION);
        return areaRepository.findAll().stream()
                .map(area -> convertToAreaDto(area))
                .collect(Collectors.toList());
    }   
    //obtiene el area por id
    @Override
    public AreaDto getAreaById(short id_area) throws ResourceNotFoundException {
        Area area=returnArea(id_area);
        return convertToAreaDto(area);
    }
    //guarda un staff pasamos id de area y de posicion
    @Override
    public MessageHandler saveNewStaff(short id_area,short id_position,StaffDto dto)
            throws ResourceNotFoundException, BusinesException, MessagingException,
            ApiException, InterruptedException, IOException{
        
        if(staffRepository.existsByDni(dto.getDni()))
            throw new BusinesException(MessageHandler.ALREADY_EXISTS);
        
        Area area =returnArea(id_area);
        Position position= returnPosition(id_position);
        
        Staff staff= new Staff(
                dto.getName(),
                dto.getSurname(),
                dto.getAddress(),
                dto.getDni(),
                dto.getBorn(),
                area,
                position,
                dto.getContractStart(),
                dto.getEmail());
        //asignamos las coordenadas del objeto direccion del staff y guardamos en ddbb
        staff.setAddressCoordinates(googleMapsServices.getCoordinates(dto.getAddress()).toString());
        staffRepository.save(staff);
        //va despues o no se podra crear por el uuid
        if(
            !staff.getArea().getArea().equals(AreaEnum.RRHH)
                &&
            !staff.getArea().getArea().equals(AreaEnum.EJECUTIVO)
           ){
           saveUsuarioByStaff(staff);
           mailService.sendWelcom(staff);
           
        }
        return new MessageHandler(MessageHandler.CREATED, HttpStatus.CREATED);
    }
        
    //utilizamos dtoAddress para actualizar domicilio de staff, pasamos dni para saber cual hay que actualizar
    @Override
    public MessageHandler updateAddressOfStaff(int dni, Address address)
            throws ResourceNotFoundException,ApiException, InterruptedException, IOException{
        Staff staff=returnStaff(dni);
        staff.setAddressCoordinates(address.toString());
        staffRepository.save(staff);
        
        MessageHandler msg=new MessageHandler(MessageHandler.UPDATED, HttpStatus.OK);
        return msg;
    }
    //modificamos la posicion de staff, junto con el salario correspondiente al area
    @Override
    public MessageHandler updatePositionOfStaff(
            int dni,
            short id_position,
            StaffDtoAcenso dto)throws ResourceNotFoundException{
        
        Staff staff=returnStaff(dni);
        Position position= returnPosition(id_position);
        
        if(staff.getPosition()!= position){
            throw new BusinesException("Ese puesto no corresponde a ese personal.");
        }
        //debemos remover el staff de la lista anterior del puesto pasando su id
        position.getStaff().remove(staff);
        
        //obtenemos la posicion del id dto
        Position updateByDto=returnPosition(dto.getId_position());
        //a staff le asignamos una nueva posicion
        staff.setPosition(updateByDto);
        //actualizamos el sueldo bsico del staff con el de la posicion que le corresponde
        staff.setGrossSalary(updateByDto.getBasicSalary());
        
        staffRepository.save(staff);
        //tenemos que asignar este staff a la lista nueva posicion
        updateByDto.addStaff(staff);
        
        return new MessageHandler(MessageHandler.UPDATED, HttpStatus.OK);
    }
    //eliminar staff y remover de la lista de posicion y area
    @Override
    public MessageHandler deleteStaff(int dni) throws ResourceNotFoundException {
        Staff staff=returnStaff(dni);
        
        //sacamos staf de la lista de position para liberar memoria
        Position position=positionRepository.findByPosition(staff.getPosition().getPosition()).get();
        position.getStaff().remove(staff);
        //eliminamos staff
        staffRepository.delete(staff);
        
        return new MessageHandler(MessageHandler.ELIMINATED, HttpStatus.OK);
    }
    //obtiene un staff por dni
    @Override
    public Staff getOneByDni(int dni){
        Staff staff=returnStaff(dni);
        return staff;
    }
}