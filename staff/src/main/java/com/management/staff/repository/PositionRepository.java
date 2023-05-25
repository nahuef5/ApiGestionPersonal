package com.management.staff.repository;
import com.management.staff.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PositionRepository extends JpaRepository<Position, Short>{}