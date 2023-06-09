package com.management.staff.security.services.userService;
import com.management.staff.entities.Staff;
import com.management.staff.global.exceptions.BusinesException;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.repository.StaffRepository;
import com.management.staff.security.dto.token.JwtToken;
import com.management.staff.security.dto.user.*;
import com.management.staff.security.entities.*;
import com.management.staff.security.enums.RoleEnum;
import com.management.staff.security.jwt.JwtProvider;
import com.management.staff.security.repository.UsuarioRepository;
import com.management.staff.security.services.roleService.RoleService;
import java.text.ParseException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UsuarioService implements CreateUserInterface{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StaffRepository staffRepository;

    private boolean existsUser(NewUsuarioDto dto){
        return usuarioRepository.existsByDni(dto.getDni()) ||
               usuarioRepository.existsByUsername(dto.getUsername())||
               usuarioRepository.existsByEmail(dto.getEmail());
    }
    //METHODS CREATE

    @Override
    public MessageHandler createAdmin(NewUsuarioDto dto) throws BusinesException {
        if(existsUser(dto))
            throw new BusinesException(MessageHandler.ALREADY_EXISTS);
        if(!dto.getPassword().equals(dto.getPasswordConfirm()))
            throw new BusinesException("Las contraseñas no coinciden.");
        
        String passwordEncode= passwordEncoder.encode(dto.getPasswordConfirm());
        Usuario user=new Usuario(
                                dto.getDni(),
                                dto.getEmail(),
                                dto.getUsername(),
                                passwordEncode);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleByName(RoleEnum.ROLE_USUARIO).get());
        roles.add(roleService.getRoleByName(RoleEnum.ROLE_ADMINTRAINEE).get());
        roles.add(roleService.getRoleByName(RoleEnum.ROLE_ADMIN).get());
        user.setRoles(roles);
        usuarioRepository.save(user);
        return new MessageHandler("Usuario administrador creado", HttpStatus.CREATED);
    }

    @Override
    public MessageHandler createTrainee(NewUsuarioDto dto) throws BusinesException {
        if(existsUser(dto))
            throw new BusinesException(MessageHandler.ALREADY_EXISTS);
        if(!dto.getPassword().equals(dto.getPasswordConfirm()))
            throw new BusinesException("Las contraseñas no coinciden.");
        Staff staff = staffRepository.findByDni(dto.getDni()).orElseThrow(
                ()->new BusinesException("No existe personal con ese dni"));
        String passwordEncode= passwordEncoder.encode(dto.getPasswordConfirm());
        Usuario user=new Usuario(
                                dto.getDni(),
                                dto.getEmail(),
                                dto.getUsername(),
                                passwordEncode);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleByName(RoleEnum.ROLE_USUARIO).get());
        roles.add(roleService.getRoleByName(RoleEnum.ROLE_ADMINTRAINEE).get());
        user.setRoles(roles);
        usuarioRepository.save(user);
        return new MessageHandler("Usuario trainee creado", HttpStatus.CREATED);
    }
    @Override
    public MessageHandler createUserEjecutivo(NewUsuarioDto dto) throws BusinesException {
        if(existsUser(dto))
            throw new BusinesException(MessageHandler.ALREADY_EXISTS);
        if(!dto.getPassword().equals(dto.getPasswordConfirm()))
            throw new BusinesException("Las contraseñas no coinciden");
        Staff staff = staffRepository.findByDni(dto.getDni()).orElseThrow(
                ()->new BusinesException("No existe un ejecutivo con ese dni"));
        String passwordEncode= passwordEncoder.encode(dto.getPasswordConfirm());
        Usuario user=new Usuario(
                                dto.getDni(),
                                dto.getEmail(),
                                dto.getUsername(),
                                passwordEncode);
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleByName(RoleEnum.ROLE_EJECUTIVO).get());
        user.setRoles(roles);
        usuarioRepository.save(user);
        return new MessageHandler("Usuario ejecutivo creado", HttpStatus.CREATED);
    }

    //LOGIN
    public JwtToken loginUser(LoginUserDto dto){
        
        Authentication authentication=authenticationManager.authenticate(
                                    new UsernamePasswordAuthenticationToken(
                                    dto.getUsername(),
                                    dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtToken(token);
    }
    public JwtToken getRefreshToken(JwtToken jwtToken) throws ParseException{
        String token=jwtProvider.generateRefreshToken(jwtToken);
        JwtToken jwt= new JwtToken(token);
        return jwt;
    }
}