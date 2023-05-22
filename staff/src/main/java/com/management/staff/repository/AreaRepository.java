package com.management.staff.repository;
import com.management.staff.entities.Area;
import com.management.staff.enums.AreaEnum;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AreaRepository extends JpaRepository<Area, Short>{
    Optional<Area>findByArea(AreaEnum areaEnum);
}