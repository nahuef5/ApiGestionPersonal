package com.management.staff.repository;
import com.management.staff.entities.Staff;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StaffRepository extends JpaRepository<Staff, String>{
    //DNI es unico no pueden existir 2 personas con el mismo documento
    Optional<Staff>findByDni(int dni);
    boolean existsByDni(int dni);
}