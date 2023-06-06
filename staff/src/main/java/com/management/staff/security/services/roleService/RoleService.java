package com.management.staff.security.services.roleService;
import com.management.staff.security.entities.Role;
import com.management.staff.security.enums.RoleEnum;
import com.management.staff.security.repository.RoleRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Optional<Role> getRoleByName(RoleEnum roleEnum){
        return roleRepository.findByRole(roleEnum);
    }
}