package com.management.staff.security.config;
import com.management.staff.security.jwt.*;
import com.management.staff.security.services.userDetailsServiceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Autowired
    private JwtAuthenticationFilter authenticationFilter;
    AuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        authenticationManager=builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        httpSecurity.csrf().disable();
        httpSecurity.cors();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers(
                        "/",
                        "/email",
                        "/email/**",
                        "/refresh-token",
                        "/register",
                        "/staff/",
                        "/onlystaff/getOne/**",
                        "/reg-admin"
                ).permitAll()
        .anyRequest().authenticated();
        httpSecurity.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}