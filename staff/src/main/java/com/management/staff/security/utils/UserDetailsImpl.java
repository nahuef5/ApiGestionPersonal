package com.management.staff.security.utils;
import com.management.staff.security.entities.Usuario;
import java.util.*;
import java.util.stream.Collectors;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails{
    private int dni;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean expired=false;
    private boolean locked=false;
    private boolean credentialsExpired=false;
    private boolean disabled=false;
    //Constructor
    public UserDetailsImpl(int dni, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    //Patron build
    public static UserDetailsImpl build(Usuario user){
        List <GrantedAuthority> authorities =
                user
                    .getRoles()
                    .stream()
                    .map(
                        rol -> new SimpleGrantedAuthority(
                rol.getRole().name())).collect(Collectors.toList()
                );
        
        return new UserDetailsImpl(user.getDni(),
                user.getEmail(),
                user.getPassword(),
                authorities
                );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    public int getDni() {
        return dni;
    }
    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }
    @Override
    public boolean isEnabled() {
        return !disabled;
    }
}