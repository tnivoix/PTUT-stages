package fr.jfc.ptut.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

import fr.jfc.ptut.entity.Stage;

public interface StageRepository extends JpaRepository<Stage, Integer> {


    default List<Stage> findByIntershipState(String intershipState){
        List<Stage> interships = findAll();
        List<Stage> intershipsByState = new ArrayList<>();
        for (Stage i : interships) {
            if(i.getEtatStage().getNom().equals(intershipState)){
                intershipsByState.add(i);
            }
        }
        return intershipsByState;
    }

    default List<Stage> inProgressInterships(){
        List<Stage> interships = findAll();
        List<Stage> intershipsByState = new ArrayList<>();
        for (Stage i : interships) {
            String intershipState = i.getEtatStage().getNom();
            if(!intershipState.equals("Proposition en attente de validation") && !intershipState.equals("Proposition validée") && !intershipState.equals("Stage terminé")){
                intershipsByState.add(i);
            }
        }
        return intershipsByState;
    }
}