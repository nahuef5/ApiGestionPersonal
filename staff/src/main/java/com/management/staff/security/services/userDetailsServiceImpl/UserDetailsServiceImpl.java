package com.management.staff.security.services.userDetailsServiceImpl;
import com.management.staff.security.entities.Usuario;
import com.management.staff.security.repository.UsuarioRepository;
import com.management.staff.security.utils.UserDetailsImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario>usuario=usuarioRepository.findByEmail(email);
        if(!usuario.isPresent())
            throw new UsernameNotFoundException("No existe ese usuario.");
        return UserDetailsImpl.build(usuario.get());
    }
}