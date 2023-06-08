package com.management.staff.security.services.securityMail.mailService;
import com.management.staff.global.exceptions.BusinesException;
import com.management.staff.global.exceptions.ResourceNotFoundException;
import com.management.staff.global.utils.MessageHandler;
import com.management.staff.security.dto.changePassword.ChangePasswordDto;
import com.management.staff.security.entities.Usuario;
import com.management.staff.security.repository.UsuarioRepository;
import com.management.staff.security.services.securityMail.dto.EmailSenderDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
@Service
public class MailService{
    @Autowired
    private JavaMailSender javaMail;
    @Autowired
    private TemplateEngine templateEngine;
    
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${mail.urlFront}")
    private String urlFront;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    private final String SUBJECT="Cambio de contraseña";
    
    private String recoverPassword(){
        UUID uuid = UUID.randomUUID();
        String recoverPassword=uuid.toString();
        return recoverPassword;
    }
    private Usuario getUsuarioByUsername(String username){
        Usuario user=usuarioRepository.findByUsername(username)
                .orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return user;
    }
    private Usuario getUsuarioByForgottenPassword(String forgottenPassword){
        Usuario user=usuarioRepository.findByForgottenPassword(forgottenPassword)
                .orElseThrow(()->new ResourceNotFoundException(MessageHandler.NOT_FOUD));
        return user;
    }
    private void sendEmail(EmailSenderDto dto) throws MessagingException{
        MimeMessage message=javaMail.createMimeMessage();
        try{
            MimeMessageHelper messageHelper= new MimeMessageHelper(message, true);
            Context context= new Context();
            Map<String, Object>model=new HashMap<>();
            model.put("username", dto.getUsername());
            model.put("url", urlFront + dto.getForgottenPassword());
            context.setVariables(model);
            String html=templateEngine.process("email", context);
            messageHelper.setFrom(dto.getMailFrom());
            messageHelper.setTo(dto.getMailTo());
            messageHelper.setSubject(dto.getSubject());
            messageHelper.setText(html, true);
            
            javaMail.send(message);
        }
        catch(MessagingException e){
            throw new MessagingException("Problemas al enviar el mail: ",e);
        }
    }
    public MessageHandler sendRecover(EmailSenderDto dto) throws MessagingException{
        Usuario user=getUsuarioByUsername(dto.getUsername());
        
        dto.setMailFrom(mailFrom);
        dto.setMailTo(user.getEmail());
        dto.setSubject(SUBJECT);
        dto.setUsername(user.getUsername());
        
        String password=recoverPassword();
        
        dto.setForgottenPassword(password);
        user.setForgottenPassword(password);
        
        usuarioRepository.save(user);
        sendEmail(dto);
        
        MessageHandler message=new MessageHandler(MessageHandler.RESET_PASSWORD, HttpStatus.OK);
        return message;
    }
    public MessageHandler changePassword(ChangePasswordDto dto){
        Usuario user= getUsuarioByForgottenPassword(dto.getForgottenPassword());
        
        if(!dto.getPassword().equals(dto.getConfirmPassword()))
            throw new BusinesException("Las contraseñas no coinciden.");
        
        String newPassword=passwordEncoder.encode(dto.getConfirmPassword());
        user.setPassword(newPassword);
        user.setForgottenPassword(null);
        usuarioRepository.save(user);
        MessageHandler message= new MessageHandler(MessageHandler.PASSWORD_CHANGED, HttpStatus.CREATED);
        return message;
    }
}