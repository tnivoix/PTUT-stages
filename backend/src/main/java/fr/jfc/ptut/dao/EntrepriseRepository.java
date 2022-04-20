package fr.jfc.ptut.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Entreprise;
import fr.jfc.ptut.entity.Stage;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

    default List<Stage> internshipsByCompany(int idCompany) {
        return findById(idCompany).get().getStages();
    }
}
