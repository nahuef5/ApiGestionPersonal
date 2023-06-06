package com.management.staff.security.services;
import com.management.staff.security.entities.Role;
import com.management.staff.security.enums.RoleEnum;
import com.management.staff.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class CreationRolesService implements CommandLineRunner{
    @Autowired
    private RoleRepository repository;
    private void saveRole(){
        for(RoleEnum roleEnum:RoleEnum.values()){
            Role role=new Role(roleEnum);
            repository.save(role);
        }
    }
    @Override
    public void run(String... args) throws Exception {
        if(repository.findAll().isEmpty())
            saveRole();
    }
}