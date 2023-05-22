package com.management.staff.services.areaService;
import com.management.staff.entities.Area;
import com.management.staff.enums.AreaEnum;
import com.management.staff.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.stereotype.Component;

//Creara los objetos Area una vez inicializada la app, pero solo si la BBDD esta vacia.
@Component
public class AreaInitialization implements CommandLineRunner {
    @Autowired
    private AreaRepository repository;

    private void createArea(){
        for(AreaEnum enums: AreaEnum.values()){
            Area area =new Area(enums);
            repository.save(area);
        }
    }
    @Override
    public void run(String... args) throws Exception {
        if(repository.findAll().isEmpty())
            createArea();
    }
}