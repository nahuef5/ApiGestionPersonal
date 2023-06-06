package com.management.staff.security.repository;
import com.management.staff.security.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario>findByEmail(String email);
    Optional<Usuario>findByDni(int dni);
    boolean existsByDni(int dni);
    boolean existsByEmail(String email);
}