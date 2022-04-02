package fr.jfc.ptut.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Stage;

public interface StageRepository extends JpaRepository<Stage, Integer> {

    
}
