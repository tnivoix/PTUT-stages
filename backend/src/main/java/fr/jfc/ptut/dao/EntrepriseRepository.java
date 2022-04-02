package fr.jfc.ptut.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

    
}
