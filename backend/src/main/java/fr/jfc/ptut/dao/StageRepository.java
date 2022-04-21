package fr.jfc.ptut.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.jfc.ptut.entity.EtatStage;
import fr.jfc.ptut.entity.Stage;
import fr.jfc.ptut.entity.Utilisateur;

public interface StageRepository extends JpaRepository<Stage, Integer> {

    Optional<Stage> findById(Integer id);

    default List<Stage> findByInternshipState(String internshipState){
        List<Stage> internships = findAll();
        List<Stage> internshipsByState = new ArrayList<>();
        for (Stage i : internships) {
            if(i.getEtatStage().getNom().equals(internshipState)){
                internshipsByState.add(i);
            }
        }
        return internshipsByState;
    }

    default List<Stage> inProgressInternships(){
        List<Stage> internships = findAll();
        List<Stage> internshipsByState = new ArrayList<>();
        for (Stage i : internships) {
            String internshipState = i.getEtatStage().getNom();
            if(!internshipState.equals("Proposition en attente de validation") && !internshipState.equals("Proposition validée") && !internshipState.equals("Stage terminé")){
                internshipsByState.add(i);
            }
        }
        return internshipsByState;
    }

    default void changeInternshipState(Stage stage, EtatStage etatStage){
        stage.setEtatStage(etatStage);
        save(stage);
    }

    default void addSoutenanceAndJury(Stage stage, Date soutenance, String jury){
        stage.setSoutenance(soutenance);
        stage.setJury(jury);
        save(stage);
    }

    default void addUser(Stage stage, Utilisateur user){
        stage.addUtilisateur(user);
        save(stage);
    }
}