package com.management.staff.security.jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.staff.global.utils.MessageHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        MessageHandler message= new MessageHandler("Token no encontrado o invalido",HttpStatus.UNAUTHORIZED);
        response.setContentType("application/json");
        response.setStatus(message.getStatus().value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(message));
        response.getWriter().flush();
        response.getWriter().close();
    }
}