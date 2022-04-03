package fr.jfc.ptut.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.EtatStage;

public interface EtatStageRepository extends JpaRepository<EtatStage, Integer> {

    
}
