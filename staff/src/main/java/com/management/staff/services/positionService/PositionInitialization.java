package com.management.staff.services.positionService;
import com.management.staff.entities.Position;
import com.management.staff.enums.PositionEnum;
import com.management.staff.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class PositionInitialization implements CommandLineRunner{
    @Autowired
    private PositionRepository repository;
    private void createPosition(){
        for(PositionEnum positionEnum: PositionEnum.values()){
            Position position=new Position(positionEnum);
            repository.save(position);
        }
    }
    @Override
    public void run(String... args) throws Exception{
        if(repository.findAll().isEmpty())
            createPosition();
    }
}