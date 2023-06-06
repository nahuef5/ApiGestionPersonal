package com.management.staff.security.repository;
import com.management.staff.security.entities.Role;
import com.management.staff.security.enums.RoleEnum;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Short>{Optional<Role>findByRole(RoleEnum roleEnum);}